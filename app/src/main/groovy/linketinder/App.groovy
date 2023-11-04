package linketinder

import linketinder.control.JobController
import linketinder.control.UserController
import linketinder.data.CandidateDAO
import linketinder.data.CompanyDAO
import linketinder.data.CompetencyDAO
import linketinder.data.JobDAO
import linketinder.view.CandidateView
import linketinder.view.CompanyView
import linketinder.view.JobView
import linketinder.view.MenuView
import linketinder.view.NewUserView

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
NewUserView newUserView = new NewUserView(candidateView, companyView, scanner)
JobView jobView = new JobView(jobController, scanner)

MenuView menu = new MenuView(scanner, candidateView, companyView, jobView, newUserView)
menu.drawMainMenu()
scanner.close()
