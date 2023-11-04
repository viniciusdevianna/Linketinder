package linketinder.view

import linketinder.control.UserController
import linketinder.model.Company
import linketinder.model.User
import linketinder.model.Address

class CompanyView extends UserView{
    private List<Company> companies

    CompanyView(UserController userController, Scanner scanner) {
        super(scanner, userController)
        this.companies = this.controller.getAllUsers() as List<Company>
    }

    void drawUsersMenu() {
        this.companies.each {println it}
        Integer option = 0
        while (option != 3) {
            println "O que você deseja fazer?"
            println "1 - Editar empresa"
            println "2 - Apagar empresa"
            println "3 - Voltar"

            option = scanner.nextInt()
            switch (option) {
                case 1:
                    println "Qual empresa deseja editar? Selecione o Id:"
                    Integer companyId = scanner.nextInt()
                    Company company = companies.find {it.idCompany == companyId}
                    scanner.nextLine()
                    this.drawEditCompanyMenu(company)
                    break
                case 2:
                    println "Qual empresa deseja apagar? Selecione o Id:"
                    Integer companyId = scanner.nextInt()
                    Company company = companies.find {it.idCompany == companyId}
                    scanner.nextLine()
                    this.drawDeleteUserMenu(company)
                    break
                case 3:
                    println "Voltando..."
                    break
                default:
                    println "Opção inválida"
            }
        }
    }

    void drawEditCompanyMenu(Company company) {
        Company editedCompany = this.getEditUserInfo(company) as Company
        println "CNPJ: ${company.cnpj}"
        String newUserCNPJ = scanner.nextLine()
        if (newUserCNPJ != "")  editedCompany.cnpj = newUserCNPJ
        this.controller.updateUser(editedCompany)
    }

    void drawNewUserMenu() {
        User newCompany = this.getNewUserGeneralInfo()
        Address newCompanyAddress = this.getNewUserAddress()

        print "CNPJ: "
        String newCompanyCNPJ = scanner.nextLine()
        Company newCompanyComplete = new Company(
                name: newCompany.name,
                email: newCompany.email,
                description: newCompany.description,
                address: newCompanyAddress,
                cnpj: newCompanyCNPJ
        )
        this.controller.saveUser(newCompanyComplete)
    }
}
