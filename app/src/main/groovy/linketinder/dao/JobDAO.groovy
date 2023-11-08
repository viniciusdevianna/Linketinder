package linketinder.dao

import groovy.sql.Sql
import linketinder.dao.interfaces.JobDaoInterface
import linketinder.model.Job

class JobDAO implements JobDaoInterface{
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
                    job.competencies = this.competencyDAO.getCompetencyByJob(job.idJob)
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
                    job.competencies = this.competencyDAO.getCompetencyByJob(job.idJob)
                    companyJobs.add(job)
                }
            }
        } catch (Exception e) {
            println e
        }

        return companyJobs
    }

    void save(Job job) {
        try {
            Integer newJobId = 0
            DatabaseConnector.executeInstance {
                Sql sql -> sql.withTransaction {
                    sql.execute("""INSERT INTO jobs (id_company, description, locale) VALUES 
                        (${job.idCompany}, ${job.description}, ${job.location})""")
                    newJobId = sql.firstRow("SELECT currval(pg_get_serial_sequence('jobs', 'id_job'))").values()[0] as Integer
                }
            }
            this.competencyDAO.addJobCompetencies(newJobId, job.competencies)
        } catch (Exception e) {
            println e
        }

    }

    void delete(Job job) {
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.execute("DELETE FROM jobs WHERE id_job = ${job.idJob}")
            }
        } catch (Exception e) {
            println e
        }
    }

    void update(Job job) {
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.execute("UPDATE jobs SET description = ${job.description}, locale = ${job.location} WHERE id_job = ${job.idJob}")
            }
            this.competencyDAO.updateJobCompetencies(job.idJob, job.competencies)
        } catch (Exception e) {
            println e
        }
    }
}
