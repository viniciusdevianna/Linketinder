package data

import groovy.sql.Sql
import model.util.Competency

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

    List<Competency> getCompetencyByCandidate(Integer idCandidate) {
        List userCompetencies = []
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        """SELECT a.id_competency, a.language FROM competencies a, candidate_competency b 
                           WHERE a.id_competency = b.id_competency AND b.id_candidate = ${idCandidate}""",
                ) {
                    Competency competency = new Competency(idCompetency: it.id_competency, language: it.language)
                    userCompetencies.add(competency)
                }
            }
        } catch (Exception e) {
            println e
        }

        return userCompetencies
    }
}
