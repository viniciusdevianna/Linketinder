package view

import control.UserController
import model.Candidate
import model.Company
import model.User
import model.util.Address
import model.util.CNPJ
import model.util.CPF

class MenuView {
    private final scanner
    private final candidateControl
    private final companyControl

    MenuView(Scanner scanner, UserController candidateControl, UserController companyControl) {
        this.scanner = scanner
        this.candidateControl = candidateControl
        this.companyControl = companyControl
    }

    void drawMainMenu() {
        Integer option = 0
        while (option != 4) {
            println "O que você deseja fazer?"
            println "1 - Listar candidatos"
            println "2 - Listar empresas"
            println "3 - Cadastrar novo usuário"
            println "4 - Sair"

            option = scanner.nextInt()
            switch (option) {
                case 1:
                    this.candidateControl.getAllUsers().each { println it }
                    break
                case 2:
                    this.companyControl.getAllUsers().each { println it }
                    break
                case 3:
                    this.drawNewUserMenu()
                    break
                case 4:
                    println "Saindo do aplicativo..."
                    break
                default:
                    println "Opção inválida"
            }
        }
    }

    void drawNewUserMenu() {
        Integer newUserOption = 0
        while (newUserOption != 3) {
            println "Que tipo de novo usuário você deseja adicionar?"
            println "1 - Candidato"
            println "2 - Empresa"
            println "3 - Cancelar"

            newUserOption = scanner.nextInt()
            if (newUserOption == 3) {
                println "Voltando para o menu principal..."
                continue
            }

            scanner.nextLine()
            User newUser = this.getNewUserGeneralInfo()
            Address newUserAddress = this.getNewUserAddress()
            switch (newUserOption) {
                case 1:
                    print "CPF: "
                    CPF newUserCPF = new CPF(
                        number: scanner.nextLine()
                    )
                    print "Idade: "
                    Integer newUserAge = scanner.nextInt()
                    scanner.nextLine()
                    println "Insira sua formação, separando cada item por vírgula"
                    List newUserEducation = scanner.nextLine().split(",")
                    println "Insira seus idiomas, separando cada item por vírgula"
                    List newUserLanguages = scanner.nextLine().split(",")
                    Candidate newCandidate = new Candidate(
                            name: newUser.name,
                            email: newUser.email,
                            description: newUser.description,
                            address: newUserAddress,
                            competencies: newUser.competencies,
                            cpf: newUserCPF,
                            age: newUserAge,
                            education: newUserEducation,
                            languages: newUserLanguages
                    )
                    this.candidateControl.saveUser(newCandidate)
                    break
                case 2:
                    print "CNPJ: "
                    CNPJ newUserCNPJ = new CNPJ(
                            number: scanner.nextLine()
                    )
                    Company newCompany = new Company(
                            name: newUser.name,
                            email: newUser.email,
                            description: newUser.description,
                            address: newUserAddress,
                            competencies: newUser.competencies,
                            cnpj: newUserCNPJ,
                            nOpenJobs: 0,
                            nJobsFullfilled: 0
                    )
                    companyControl.saveUser(newCompany)
                    break
                default:
                    println "Opção inválida"
            }
        }
    }

    private Address getNewUserAddress() {
        println "Insira seu endereço"
        print "País: "
        String newUserCountry = scanner.nextLine()
        print "Estado: "
        String newUserState = scanner.nextLine()
        print "Insira seu CEP apenas com números: "
        String newUserCep = scanner.nextLine()
        print "Rua: "
        String newUserStreet = scanner.nextLine()
        print "Número: "
        Integer newUserNumber = scanner.nextInt()
        scanner.nextLine()
        print "Complemento: "
        String newUserComplement = scanner.nextLine()
        return new Address(
                country: newUserCountry,
                state: newUserState,
                cep: newUserCep,
                street: newUserStreet,
                number: newUserNumber,
                complement: newUserComplement
        )
    }

    private User getNewUserGeneralInfo() {
        print "Nome: "
        String newUserName = scanner.nextLine()
        print "E-mail: "
        String newUserEmail = scanner.nextLine()
        println "Insira uma breve descrição sobre você"
        String newUserDescription = scanner.nextLine()
        println "Insira suas competências, separando cada item por vírgula"
        List newUserCompetencies = scanner.nextLine().split(",")
        return new User(
                name: newUserName,
                email: newUserEmail,
                description: newUserDescription,
                competencies: newUserCompetencies
        )
    }
}
