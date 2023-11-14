package linketinder.api

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import linketinder.control.UserController
import linketinder.data.CandidateDAO
import linketinder.data.CompetencyDAO
import linketinder.data.enums.DatabaseTypes
import linketinder.data.factories.DatabaseConnectorFactory
import linketinder.data.interfaces.IDatabaseConnector
import linketinder.model.Candidate

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.stream.Collectors

@WebServlet("/candidates")
class CandidateApi extends HttpServlet{
    private UserController userController

    CandidateApi() {
        IDatabaseConnector dbConnector = new DatabaseConnectorFactory().getConnector(DatabaseTypes.POSTGRESQL)
        CompetencyDAO competencyDAO = new CompetencyDAO(dbConnector)
        CandidateDAO candidateDAO = new CandidateDAO(competencyDAO, dbConnector)
        UserController userController = new UserController(candidateDAO)
        this.userController = userController
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Candidate> allCandidates = this.userController.getAllUsers() as List<Candidate>
        List candidatesJson = []
        allCandidates.each {
            JsonBuilder candidateBuilder = new JsonBuilder(it.toMap())
            candidatesJson.add(candidateBuilder.toString())
        }
        PrintWriter writer = resp.getWriter()
        resp.setContentType("application/json")
        writer.print(candidatesJson)
        writer.flush()
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining())
        JsonSlurper slurper = new JsonSlurper()
        Candidate newCandidate = new Candidate(slurper.parseText(body) as Map)
        this.userController.saveUser(newCandidate)
        resp.getWriter().print("$newCandidate.name salvo")
    }
}
