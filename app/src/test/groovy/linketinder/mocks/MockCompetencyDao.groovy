package linketinder.mocks

import linketinder.dao.interfaces.CompetencyDaoInterface
import linketinder.model.Competency

class MockCompetencyDao implements CompetencyDaoInterface{
    private List<Competency> competencies = new ArrayList<>()

    List<Competency> getAllCompetencies() {
        return this.competencies
    }

    List<Competency> getCompetencyByCandidate(Integer id) {
        return null
    }

    List<Competency> getCompetencyByJob(Integer id) {
        return null
    }

    Competency getCompetencyByLanguage(String language) {
        return null
    }

    void save(Competency newCompetency) {
        this.competencies.add(newCompetency)
    }


    void addCandidateCompetencies(Integer id, List<Competency> competencies) {

    }


    void addJobCompetencies(Integer id, List<Competency> competencies) {

    }


    void deleteCandidateCompetencies(Integer id, List<Competency> competencies) {

    }


    void deleteJobCompetencies(Integer id, List<Competency> competencies) {

    }


    void updateCandidateCompetencies(Integer id, List<Competency> competencies) {

    }


    void updateJobCompetencies(Integer id, List<Competency> competencies) {

    }

    void delete(Competency competency) {
        this.competencies -= competency
    }

    void update(Competency competency) {
        Competency competencyToUpdate = this.competencies.find {
            it.idCompetency == competency.idCompetency
        }
        this.competencies[this.competencies.indexOf(competencyToUpdate)] = competency
    }
}
