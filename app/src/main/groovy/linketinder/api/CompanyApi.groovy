package linketinder.api

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import linketinder.control.UserController
import linketinder.data.CompanyDAO
import linketinder.data.enums.DatabaseTypes
import linketinder.data.factories.DatabaseConnectorFactory
import linketinder.data.interfaces.IDatabaseConnector
import linketinder.model.Company

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.stream.Collectors

@WebServlet("/companies")
class CompanyApi extends HttpServlet{
    private UserController userController

    CompanyApi() {
        IDatabaseConnector dbConnector = new DatabaseConnectorFactory().getConnector(DatabaseTypes.POSTGRESQL)
        CompanyDAO companyDAO = new CompanyDAO(dbConnector)
        UserController userController = new UserController(companyDAO)
        this.userController = userController
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> allCompanies = this.userController.getAllUsers() as List<Company>
        List candidatesJson = []
        allCompanies.each {
            JsonBuilder companyBuilder = new JsonBuilder(it.toMap())
            candidatesJson.add(companyBuilder.toString())
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
        Company newCompany = new Company(slurper.parseText(body) as Map)
        this.userController.saveUser(newCompany)
        resp.getWriter().print("$newCompany.name salvo")
    }
}
