import control.JobController
import control.UserController
import data.CandidateDAO
import data.CompanyDAO
import data.CompetencyDAO
import data.JobDAO
import view.MenuView

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


