package linketinder.mocks

import linketinder.data.interfaces.IJobDao
import linketinder.model.Job

class MockJobDao implements IJobDao{
    private List<Job> jobs = new ArrayList<>()

    List<Job> getAllJobs() {
        return this.jobs
    }

    List<Job> getJobsByCompany(Integer idCompany) {
        return this.jobs.findAll {it.idCompany == idCompany }
    }

    void save(Job job) {
        this.jobs.add(job)
    }

    void delete(Job job) {
        this.jobs -= job
    }

    void update(Job job) {
        Job jobToUpdate = this.jobs.find {job.idJob == it.idJob }
        this.jobs[jobs.indexOf(jobToUpdate)] = job
    }
}
