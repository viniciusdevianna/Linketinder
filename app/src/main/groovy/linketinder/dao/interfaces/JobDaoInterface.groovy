package linketinder.dao.interfaces

import linketinder.model.Job

interface JobDaoInterface {
    List<Job> getAllJobs()
    List<Job> getJobsByCompany(Integer idCompany)
    void save(Job job)
    void delete(Job job)
    void update(Job job)
}