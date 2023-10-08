package control

import data.JobDAO
import model.Company
import model.Job

class JobController {
    private JobDAO jobDAO

    JobController(JobDAO jobDAO) {
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
