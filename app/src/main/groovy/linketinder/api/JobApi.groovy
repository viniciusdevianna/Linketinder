package linketinder.api

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import linketinder.control.JobController
import linketinder.data.CompetencyDAO
import linketinder.data.JobDAO
import linketinder.data.enums.DatabaseTypes
import linketinder.data.factories.DatabaseConnectorFactory
import linketinder.data.interfaces.IDatabaseConnector
import linketinder.model.Job

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.stream.Collectors

@WebServlet("/jobs")
class JobApi extends HttpServlet{
    private JobController jobController

    JobApi() {
        IDatabaseConnector dbConnector = new DatabaseConnectorFactory().getConnector(DatabaseTypes.POSTGRESQL)
        CompetencyDAO competencyDAO = new CompetencyDAO(dbConnector)
        JobDAO jobDAO = new JobDAO(competencyDAO, dbConnector)
        JobController jobController = new JobController(jobDAO)
        this.jobController = jobController
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Job> allJobs = this.jobController.getAllJobs() as List<Job>
        List jobsJson = []
        allJobs.each {
            JsonBuilder jobBuilder = new JsonBuilder(it.toMap())
            jobsJson.add(jobBuilder.toString())
        }
        PrintWriter writer = resp.getWriter()
        resp.setContentType("application/json")
        writer.print(jobsJson)
        writer.flush()
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining())
        JsonSlurper slurper = new JsonSlurper()
        Job newJob = new Job(slurper.parseText(body) as Map)
        this.jobController.saveJob(newJob)
        resp.getWriter().print("$newJob.description salvo")
    }
}
