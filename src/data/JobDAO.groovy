package data

import groovy.sql.Sql
import model.Job

class JobDAO {

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
                    companyJobs.add(job)
                }
            }
        } catch (Exception e) {
            println e
        }

        return companyJobs
    }

    void delete(Job job) {
        DatabaseConnector.executeInstance {
            Sql sql -> sql.execute("DELETE FROM jobs WHERE id_job = ${job.idJob}")
        }
    }

    void update(Job job) {
        DatabaseConnector.executeInstance {
            Sql sql -> sql.execute("UPDATE jobs SET description = ${job.description}, locale = ${job.location}")
        }
    }
}
