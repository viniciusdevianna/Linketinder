package linketinder.control

import linketinder.data.interfaces.IJobDao
import linketinder.model.Company
import linketinder.model.Job

class JobController {
    private IJobDao jobDAO

    JobController(IJobDao jobDAO) {
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
