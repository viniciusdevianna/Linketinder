package linketinder.control

import linketinder.data.CompetencyDAO
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
        competencyDAO.addCandidateOrJobCompetency(candidate.idCandidate, "candidate", competencies)
    }

    void addCompetenciesToJob(Job job, List<Competency> competencies) {
        competencyDAO.addCandidateOrJobCompetency(job.idJob, "job", competencies)
    }

    void deleteCompetenciesFromCandidate(Candidate candidate, List<Competency> competencies) {
        competencyDAO.deleteCandidateOrJobCompetency(candidate.idCandidate, "candidate", competencies)
    }

    void deleteCompetenciesFromJob(Job job, List<Competency> competencies) {
        competencyDAO.deleteCandidateOrJobCompetency(job.idJob, "job", competencies)
    }
}
