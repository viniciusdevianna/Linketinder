package linketinder.control

import linketinder.dao.interfaces.JobDaoInterface
import linketinder.model.Company
import linketinder.model.Job

class JobController {
    private JobDaoInterface jobDAO

    JobController(JobDaoInterface jobDAO) {
        this.jobDAO = jobDAO
    }

    List<Job> getAllJobs() {
        jobDAO.getAllJobs()
    }

    List<Job> getJobsByCompany(Company company) {
        jobDAO.getJobsByCompany(company.idCompany)
    }

    void deleteJob(Job job) {
        jobDAO.delete(job)
    }

    void updateJob(Job job) {
        jobDAO.update(job)
    }

    void saveJob(Job job) {
        jobDAO.save(job)
    }
}
