package linketinder

import linketinder.control.JobController
import linketinder.control.UserController
import linketinder.dao.CandidateDAO
import linketinder.dao.CompanyDAO
import linketinder.dao.CompetencyDAO
import linketinder.dao.JobDAO
import linketinder.view.CandidateView
import linketinder.view.CompanyView
import linketinder.view.JobView
import linketinder.view.MenuView

Scanner scanner = new Scanner(System.in)
CompetencyDAO competencyDAO = new CompetencyDAO()
CandidateDAO candidateDAO = new CandidateDAO(competencyDAO)
JobDAO jobDAO = new JobDAO(competencyDAO)
CompanyDAO companyDAO = new CompanyDAO()

UserController candidateController = new UserController(candidateDAO)
UserController companyController = new UserController(companyDAO)
JobController jobController = new JobController(jobDAO)

CandidateView candidateView = new CandidateView(candidateController, scanner)
CompanyView companyView = new CompanyView(companyController, scanner)
JobView jobView = new JobView(jobController, scanner)

MenuView menu = new MenuView(scanner, candidateView, companyView, jobView)
menu.drawMainMenu()
scanner.close()
