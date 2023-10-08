package linketinder.data

import groovy.sql.Sql
import linketinder.model.util.Competency

class CompetencyDAO {

    List<Competency> getAllCompetencies() {
        List allCompetencies = []
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        "SELECT id_competency, language FROM competencies"
                ) {
                    Competency competency = new Competency(it as Map)
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

    void update(Competency competency) {
        DatabaseConnector.executeInstance {
            Sql sql -> sql.executeUpdate("UPDATE competencies SET language = ${competency.language} WHERE id_competency = ${competency.idCompetency}")
        }
    }

    void delete(Competency competency) {
        DatabaseConnector.executeInstance {
            Sql sql -> sql.execute("DELETE FROM competencies WHERE id_competency = ${competency.idCompetency}")
        }
    }

    void save(Competency competency) {
        DatabaseConnector.executeInstance {
            Sql sql -> sql.execute("""INSERT INTO competencies (language) SELECT ${competency.language}
                WHERE NOT EXISTS (SELECT language FROM competencies WHERE language = ${competency.language}""")
        }
    }

    void addCandidateOrJobCompetency(Integer id, String candidateOrJob, List<Competency> competencies) {
        for (competency in competencies) {
            this.save(competency)
            DatabaseConnector.executeInstance {
                Sql sql -> sql.executeInsert("""INSERT INTO ${candidateOrJob}_competency (id_${candidateOrJob}, id_competency)
                SELECT ${id}, ${competency.idCompetency}
                WHERE NOT EXISTS (SELECT id_${candidateOrJob}, id_competency FROM ${candidateOrJob}_competency 
                WHERE id_${candidateOrJob} = ${id} AND id_competency = ${competency.idCompetency}""")
            }
        }
    }

    void deleteCandidateOrJobCompetency(Integer id, String candidateOrJob, List<Competency> competencies) {
        for (competency in competencies) {
            DatabaseConnector.executeInstance {
                Sql sql ->
                    sql.executeInsert("""DELETE FROM ${candidateOrJob}_competency WHERE
                    id_${candidateOrJob} = ${id} and id_competency = ${competency.idCompetency}""")
            }
        }
    }
}
