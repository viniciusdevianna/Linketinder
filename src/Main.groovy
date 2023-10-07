import control.UserController
import data.CandidateDAO
import data.CompanyDAO
import view.MenuView

Scanner scanner = new Scanner(System.in)
CandidateDAO candidateDAO = new CandidateDAO()
CompanyDAO companyDAO = new CompanyDAO()
UserController candidateController = new UserController(candidateDAO)
UserController companyController = new UserController(companyDAO)

MenuView menu = new MenuView(scanner, candidateController, companyController)
menu.drawMainMenu()


