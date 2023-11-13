package linketinder

import linketinder.control.JobController
import linketinder.control.UserController
import linketinder.data.CandidateDAO
import linketinder.data.CompanyDAO
import linketinder.data.CompetencyDAO
import linketinder.data.JobDAO
import linketinder.data.enums.DatabaseTypes
import linketinder.data.factories.DatabaseConnectorFactory
import linketinder.data.interfaces.IDatabaseConnector
import linketinder.view.CandidateView
import linketinder.view.CompanyView
import linketinder.view.JobView
import linketinder.view.MenuView

import java.security.InvalidParameterException

try {
    Scanner scanner = new Scanner(System.in)

    IDatabaseConnector dbConnector = new DatabaseConnectorFactory().getConnector(DatabaseTypes.POSTGRESQL)
    CompetencyDAO competencyDAO = new CompetencyDAO(dbConnector)
    CandidateDAO candidateDAO = new CandidateDAO(competencyDAO, dbConnector)
    JobDAO jobDAO = new JobDAO(competencyDAO, dbConnector)
    CompanyDAO companyDAO = new CompanyDAO(dbConnector)

    UserController candidateController = new UserController(candidateDAO)
    UserController companyController = new UserController(companyDAO)
    JobController jobController = new JobController(jobDAO)

    CandidateView candidateView = new CandidateView(candidateController, scanner)
    CompanyView companyView = new CompanyView(companyController, scanner)
    JobView jobView = new JobView(jobController, scanner)

    MenuView menu = new MenuView(scanner, candidateView, companyView, jobView)

    menu.drawMainMenu()
    scanner.close()
} catch (InvalidParameterException e) {
    e.printStackTrace()
    println "Finalizando programa com erro..."
} catch (Exception e) {
    e.printStackTrace()
}

