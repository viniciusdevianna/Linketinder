package linketinder

import linketinder.control.JobController
import linketinder.mocks.MockJobDao
import linketinder.model.Company
import linketinder.model.Job
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions

class JobControllerTest {
    private JobController jobController

    @Test
    void testSavingNewJob() {
        // Given
        Job newJob = new Job()
        this.jobController = new JobController(new MockJobDao())

        // When
        this.jobController.saveJob(newJob)
        List expected = [newJob]

        // Then
        Assertions.assertEquals(expected, this.jobController.getAllJobs())
    }

    @Test
    void testDeletingJob() {
        // Given
        this.jobController = new JobController(new MockJobDao())
        Job job1 = new Job()
        Job job2 = new Job()
        this.jobController.saveJob(job1)
        this.jobController.saveJob(job2)

        // When
        this.jobController.deleteJob(job1)
        List<Job> expected = [job2]

        // Then
        Assertions.assertEquals(expected, this.jobController.getAllJobs())
    }

    @Test
    void testUpdatingJob() {
        // Given
        this.jobController = new JobController(new MockJobDao())
        Job job = new Job(idJob: 1, description: "Teste")
        Job updatedJob = new Job(idJob: 1, description: "Novo")
        this.jobController.saveJob(job)

        // When
        this.jobController.updateJob(updatedJob)
        String expected = "Novo"

        // Then
        Assertions.assertEquals(expected, this.jobController.getAllJobs()[0].description)
    }

    @Test
    void testGettingJobsByCompany() {
        // Given
        this.jobController = new JobController(new MockJobDao())
        Job job1 = new Job(idCompany: 1, description: "Job da primeira empresa")
        Job job2 = new Job(idCompany: 2, description: "Job da segunda empresa")
        Company company = new Company(idCompany: 1)
        this.jobController.saveJob(job1)
        this.jobController.saveJob(job2)

        // When
        List expected = [job1]
        List result = this.jobController.getJobsByCompany(company)

        // Then
        Assertions.assertEquals(expected, result)
    }
}
