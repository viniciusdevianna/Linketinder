package linketinder.dao

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import linketinder.dao.interfaces.CompetencyDaoInterface
import linketinder.model.Competency

class CompetencyDAO implements CompetencyDaoInterface{

    List<Competency> getAllCompetencies() {
        List allCompetencies = []
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        "SELECT id_competency, language FROM competencies"
                ) {
                    Competency competency = new Competency(idCompetency: it.id_competency, language: it.language)
                    allCompetencies.add(competency)
                }
            }
        } catch (Exception e) {
            println e
        }

        return allCompetencies
    }

    List<Competency> getCompetencyByCandidate(Integer id) {
        List competencies = []
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        """SELECT a.id_competency, a.language FROM competencies a, candidate_competency b 
                           WHERE a.id_competency = b.id_competency AND b.id_candidate = ${id}""",
                ) {
                    Competency competency = new Competency(idCompetency: it.id_competency, language: it.language)
                    competencies.add(competency)
                }
            }
        } catch (Exception e) {
            println e
        }

        return competencies
    }

    List<Competency> getCompetencyByJob(Integer id) {
        List competencies = []
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        """SELECT a.id_competency, a.language FROM competencies a, job_competency b 
                           WHERE a.id_competency = b.id_competency AND b.id_job = ${id}""",
                ) {
                    Competency competency = new Competency(idCompetency: it.id_competency, language: it.language)
                    competencies.add(competency)
                }
            }
        } catch (Exception e) {
            println e
        }

        return competencies
    }

    Competency getCompetencyByLanguage(String language) {
        Competency competency = new Competency()
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> GroovyRowResult result = sql.firstRow(
                        "SELECT id_competency, language FROM competencies WHERE language = ${language}"
                )
                    competency.idCompetency = result.id_competency as Integer
                    competency.language = result.language
            }
        } catch (Exception e) {
            println e
        }

        return competency.idCompetency ? competency : null
    }

    void update(Competency competency) {
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.executeUpdate("UPDATE competencies SET language = ${competency.language} WHERE id_competency = ${competency.idCompetency}")
            }
        } catch (Exception e) {
            println e
        }
    }

    void delete(Competency competency) {
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.execute("DELETE FROM competencies WHERE id_competency = ${competency.idCompetency}")
            }
        } catch (Exception e) {
            println e
        }
    }

    void save(Competency competency) {
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.execute("""INSERT INTO competencies (language) SELECT ${competency.language}
                WHERE NOT EXISTS (SELECT language FROM competencies WHERE language = ${competency.language})""")
            }
        } catch (Exception e) {
            println e
        }
    }

    void addCandidateCompetencies(Integer id, List<Competency> competencies) {
        for (competency in competencies) {
            this.save(competency)
            Competency savedCompetency = this.getCompetencyByLanguage(competency.language)
            if (!savedCompetency) continue
            DatabaseConnector.executeInstance {
                Sql sql -> sql.executeInsert("""INSERT INTO candidate_competency (id_candidate, id_competency)
            SELECT ${id}, ${savedCompetency.idCompetency}
            WHERE NOT EXISTS (SELECT id_candidate, id_competency FROM candidate_competency 
            WHERE id_candidate = ${id} AND id_competency = ${savedCompetency.idCompetency})""")
            }
        }
    }

    void addJobCompetencies(Integer id, List<Competency> competencies) {
        for (competency in competencies) {
            this.save(competency)
            Competency savedCompetency = this.getCompetencyByLanguage(competency.language)
            if (!savedCompetency) continue
            DatabaseConnector.executeInstance {
                Sql sql -> sql.executeInsert("""INSERT INTO job_competency (id_job, id_competency)
                SELECT ${id}, ${savedCompetency.idCompetency}
                WHERE NOT EXISTS (SELECT id_job, id_competency FROM job_competency 
                WHERE id_job = ${id} AND id_competency = ${savedCompetency.idCompetency})""")
            }
        }
    }

    void deleteCandidateCompetencies(Integer id, List<Competency> competencies) {
        for (competency in competencies) {
            DatabaseConnector.executeInstance {
                Sql sql ->
                    sql.executeInsert("""DELETE FROM candidate_competency WHERE
                    id_candidate = ${id} and id_competency = ${competency.idCompetency}""")
            }
        }
    }

    void deleteJobCompetencies(Integer id, List<Competency> competencies) {
        for (competency in competencies) {
            DatabaseConnector.executeInstance {
                Sql sql ->
                    sql.executeInsert("""DELETE FROM job_competency WHERE
                    id_job = ${id} and id_competency = ${competency.idCompetency}""")
            }
        }
    }

    void updateCandidateCompetencies(Integer id, List<Competency> competencies) {
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.withTransaction {
                    sql.execute("DELETE FROM candidate_competency WHERE id_candidate = ${id}")
                    for (competency in competencies) {
                        this.save(competency)
                        Competency savedCompetency = this.getCompetencyByLanguage(competency.language)
                        if (!savedCompetency) continue
                        sql.executeInsert("""INSERT INTO candidate_competency (id_candidate, id_competency)
                            SELECT ${id}, ${savedCompetency.idCompetency}
                            WHERE NOT EXISTS (SELECT id_candidate, id_competency FROM candidate_competency 
                            WHERE id_candidate = ${id} AND id_competency = ${savedCompetency.idCompetency})""")
                    }
                }
            }
        } catch (Exception e) {
            println e
        }
    }

    void updateJobCompetencies(Integer id, List<Competency> competencies) {
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.withTransaction {
                    sql.execute("DELETE FROM job_competency WHERE id_job = ${id}")
                    for (competency in competencies) {
                        this.save(competency)
                        Competency savedCompetency = this.getCompetencyByLanguage(competency.language)
                        if (!savedCompetency) continue
                        sql.executeInsert("""INSERT INTO job_competency (id_job, id_competency)
                            SELECT ${id}, ${savedCompetency.idCompetency}
                            WHERE NOT EXISTS (SELECT id_job, id_competency FROM job_competency 
                            WHERE id_job = ${id} AND id_competency = ${savedCompetency.idCompetency})""")
                    }
                }
            }
        } catch (Exception e) {
            println e
        }
    }
}
