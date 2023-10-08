package linketinder.data

import groovy.sql.Sql
import linketinder.model.Job

class JobDAO {
    private CompetencyDAO competencyDAO

    JobDAO(CompetencyDAO competencyDAO) {
        this.competencyDAO = competencyDAO
    }

    List<Job> getAllJobs() {
        List allJobs = []
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        """SELECT id_job, id_company, description, locale FROM jobs"""
                ) {
                    Job job = new Job(
                            idJob: it.id_job,
                            idCompany: it.id_company,
                            description: it.description,
                            location: it.locale
                    )
                    job.competencies = this.competencyDAO.getCompetencyByCandidateOrJob(job.idJob, "job")
                    allJobs.add(job)
                }
            }
        } catch (Exception e) {
            println e
        }

        return allJobs
    }

    List<Job> getJobsByCompany(Integer idCompany) {
        List companyJobs = []
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        """SELECT id_job, description, locale FROM jobs WHERE id_company = ${idCompany}"""
                ) {
                    Job job = new Job(
                            idCompany: idCompany,
                            idJob: it.id_job,
                            description: it.description,
                            location: it.locale
                    )
                    job.competencies = this.competencyDAO.getCompetencyByCandidateOrJob(job.idJob, "job")
                    companyJobs.add(job)
                }
            }
        } catch (Exception e) {
            println e
        }

        return companyJobs
    }

    void save(Job job) {
        DatabaseConnector.executeInstance {
            Sql sql -> sql.execute("""INSERT INTO jobs (id_company, description, locale) VALUES 
                (${job.idCompany}, ${job.description}, ${job.location})""")
        }
    }

    void delete(Job job) {
        DatabaseConnector.executeInstance {
            Sql sql -> sql.execute("DELETE FROM jobs WHERE id_job = ${job.idJob}")
        }
    }

    void update(Job job) {
        DatabaseConnector.executeInstance {
            Sql sql -> sql.execute("UPDATE jobs SET description = ${job.description}, locale = ${job.location} WHERE id_job = ${job.idJob}")
        }
    }
}
