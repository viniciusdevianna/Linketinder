package linketinder

import linketinder.control.JobController
import linketinder.control.UserController
import linketinder.data.CandidateDAO
import linketinder.data.CompanyDAO
import linketinder.data.CompetencyDAO
import linketinder.data.JobDAO
import linketinder.view.MenuView

Scanner scanner = new Scanner(System.in)
CompetencyDAO competencyDAO = new CompetencyDAO()
CandidateDAO candidateDAO = new CandidateDAO(competencyDAO)
JobDAO jobDAO = new JobDAO(competencyDAO)
CompanyDAO companyDAO = new CompanyDAO()
UserController candidateController = new UserController(candidateDAO)
UserController companyController = new UserController(companyDAO)
JobController jobController = new JobController(jobDAO)

MenuView menu = new MenuView(scanner, candidateController, companyController, jobController)
menu.drawMainMenu()
scanner.close()
