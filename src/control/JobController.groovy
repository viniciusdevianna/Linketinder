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
}
