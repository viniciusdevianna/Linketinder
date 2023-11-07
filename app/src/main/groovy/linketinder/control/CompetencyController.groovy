package linketinder.control

import linketinder.dao.CompetencyDAO
import linketinder.model.Candidate
import linketinder.model.Job
import linketinder.model.Competency

class CompetencyController {
    private CompetencyDAO competencyDAO

    CompetencyController(CompetencyDAO competencyDAO) {
        this.competencyDAO = competencyDAO
    }

    List<Competency> getAllCompetencies() {
        competencyDAO.getAllCompetencies()
    }

    List<Competency> getCompetenciesByCandidate(Candidate candidate) {
        competencyDAO.getCompetencyByCandidate(candidate.idCandidate)
    }

    List<Competency> getCompetenciesbyJob(Job job) {
        competencyDAO.getCompetencyByJob(job.idJob)
    }

    void deleteCompetency(Competency competency) {
        competencyDAO.delete(competency)
    }

    void updateCompetency(Competency competency) {
        competencyDAO.update(competency)
    }

    void addCompetenciesToCandidate(Candidate candidate, List<Competency> competencies) {
        competencyDAO.addCandidateCompetencies(candidate.idCandidate, competencies)
    }

    void addCompetenciesToJob(Job job, List<Competency> competencies) {
        competencyDAO.addJobCompetencies(job.idJob, competencies)
    }

    void deleteCompetenciesFromCandidate(Candidate candidate, List<Competency> competencies) {
        competencyDAO.deleteCandidateCompetencies(candidate.idCandidate, competencies)
    }

    void deleteCompetenciesFromJob(Job job, List<Competency> competencies) {
        competencyDAO.deleteJobCompetencies(job.idJob, competencies)
    }
}
