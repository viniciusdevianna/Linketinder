package linketinder.data.interfaces

import linketinder.model.Competency

interface ICompetencyDao {
    List<Competency> getAllCompetencies()
    List<Competency> getCompetencyByCandidate(Integer id)
    List<Competency> getCompetencyByJob(Integer id)
    Competency getCompetencyByLanguage(String language)
    void update(Competency competency)
    void delete(Competency competency)
    void save(Competency competency)
    void addCandidateCompetencies(Integer id, List<Competency> competencies)
    void addJobCompetencies(Integer id, List<Competency> competencies)
    void deleteCandidateCompetencies(Integer id, List<Competency> competencies)
    void deleteJobCompetencies(Integer id, List<Competency> competencies)
    void updateCandidateCompetencies(Integer id, List<Competency> competencies)
    void updateJobCompetencies(Integer id, List<Competency> competencies)
}