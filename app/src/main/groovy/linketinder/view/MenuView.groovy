package linketinder.view

import linketinder.control.JobController
import linketinder.control.UserController
import linketinder.model.Candidate
import linketinder.model.Company
import linketinder.model.Job
import linketinder.model.User
import linketinder.model.util.Address
import linketinder.model.util.Competency
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MenuView {
    private final Scanner scanner
    private final UserController candidateControl
    private final UserController companyControl
    private final JobController jobControl
    List<Candidate> candidates
    List<Company> companies
    List<Job> jobs

    MenuView(Scanner scanner, UserController candidateControl, UserController companyControl, JobController jobControl) {
        this.scanner = scanner
        this.candidateControl = candidateControl
        this.companyControl = companyControl
        this.jobControl = jobControl
    }

    void drawMainMenu() {
        Integer option = 0
        while (option != 5) {
            this.candidates = candidateControl.getAllUsers() as List<Candidate>
            this.companies = companyControl.getAllUsers() as List<Company>
            this.jobs = jobControl.getAllJobs()
            println "O que você deseja fazer?"
            println "1 - Listar candidatos"
            println "2 - Listar vagas"
            println "3 - Listar empresas"
            println "4 - Cadastrar novo usuário"
            println "5 - Sair"

            option = scanner.nextInt()
            switch (option) {
                case 1:
                    this.candidates.each { println it }
                    this.drawCandidatesMenu()
                    break
                case 2:
                    this.jobs.each { println it }
                    this.drawJobsMenu()
                    break
                case 3:
                    this.companies.each { println it }
                    this.drawCompaniesMenu()
                    break
                case 4:
                    this.drawNewUserMenu()
                    break
                case 5:
                    println "Saindo do aplicativo..."
                    break
                default:
                    println "Opção inválida"
            }
        }
    }

    void drawJobsMenu() {
        Integer option = 0
        while (option != 4) {
            println "O que você deseja fazer?"
            println "1 - Editar vaga"
            println "2 - Apagar vaga"
            println "3 - Inserir vaga"
            println "4 - Voltar"

            option = scanner.nextInt()
            switch (option) {
                case 1:
                    println "Qual vaga deseja editar? Selecione o Id:"
                    Integer jobId = scanner.nextInt()
                    Job job = jobs.find {it.idJob = jobId}
                    scanner.nextLine()
                    this.drawEditJobMenu(job)
                    break
                case 2:
                    println "Qual vaga deseja apagar? Selecione o Id:"
                    Integer jobId = scanner.nextInt()
                    Job job = jobs.find {it.idJob == jobId}
                    scanner.nextLine()
                    this.drawDeleteEntityMenu(job)
                    break
                case 3:
                    this.drawNewJobMenu()
                    break
                case 4:
                    println "Voltando..."
                    break
                default:
                    println "Opção inválida"
            }
        }
    }

    void drawEditJobMenu(Job job) {
        println "Descrição: ${job.description}"
        String newJobDescription = scanner.nextLine()
        if (newJobDescription != "")  job.description = newJobDescription
        println "Local: ${job.location}"
        String newJobLocation = scanner.nextLine()
        if (newJobLocation != "")  job.location = newJobLocation
        this.jobControl.updateJob(job)
    }

    void drawCandidatesMenu() {
        Integer option = 0
        while (option != 3) {
            println "O que você deseja fazer?"
            println "1 - Editar candidato"
            println "2 - Apagar candidato"
            println "3 - Voltar"

            option = scanner.nextInt()
            switch (option) {
                case 1:
                    println "Qual candidato deseja editar? Selecione o Id:"
                    Integer candidateId = scanner.nextInt()
                    Candidate candidate = candidates.find {it.idCandidate == candidateId}
                    scanner.nextLine()
                    this.drawEditCandidateMenu(candidate)
                    break
                case 2:
                    println "Qual candidato deseja apagar? Selecione o Id:"
                    Integer candidateId = scanner.nextInt()
                    Candidate candidate = candidates.find {it.idCandidate == candidateId}
                    scanner.nextLine()
                    this.drawDeleteEntityMenu(candidate)
                    break
                case 3:
                    println "Voltando..."
                    break
                default:
                    println "Opção inválida"
            }
        }
    }

    void drawEditCandidateMenu(Candidate candidate) {
        println "Nome: ${candidate.name}"
        String newUserName = scanner.nextLine()
        if (newUserName != "")  candidate.name = newUserName
        println "E-mail: ${candidate.email}"
        String newUserEmail = scanner.nextLine()
        if (newUserEmail != "")  candidate.email = newUserEmail
        println "Descrição: ${candidate.description}"
        String newUserDescription = scanner.nextLine()
        if (newUserDescription != "")  candidate.description = newUserDescription
        println "CPF: ${candidate.cpf}"
        String newUserCPF = scanner.nextLine()
        if (newUserCPF != "")  candidate.cpf = newUserCPF
        this.candidateControl.updateUser(candidate)
    }

    void drawDeleteEntityMenu(Object object) {
        println "Tem certeza que deseja deletar (S/N)?"
        String answer = scanner.nextLine().toUpperCase()
        if (answer == "S") {
            if (object instanceof Candidate) this.candidateControl.deleteUser(object)
            if (object instanceof Company) this.companyControl.deleteUser(object)
            if (object instanceof Job) this.jobControl.deleteJob(object)
        }
    }

    void drawCompaniesMenu() {
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
                    this.drawDeleteEntityMenu(company)
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
        println "Nome: ${company.name}"
        String newUserName = scanner.nextLine()
        if (newUserName != "")  company.name = newUserName
        println "E-mail: ${company.email}"
        String newUserEmail = scanner.nextLine()
        if (newUserEmail != "")  company.email = newUserEmail
        println "Descrição: ${company.description}"
        String newUserDescription = scanner.nextLine()
        if (newUserDescription != "")  company.description = newUserDescription
        println "CNPJ: ${company.cnpj}"
        String newUserCNPJ = scanner.nextLine()
        if (newUserCNPJ != "")  company.cnpj = newUserCNPJ
        this.companyControl.updateUser(company)
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
//                    CPF newUserCPF = new CPF(
//                        number: scanner.nextLine()
//                    )
                    String newUserCPF = scanner.nextLine()
                    print "Idade: "
                    Integer newUserAge = scanner.nextInt()
                    scanner.nextLine()
                    println "Insira suas competências, separando cada item por vírgula"
                    List newUserCompetencies = []
                    List newUserCompNames = scanner.nextLine().split(",")
                    newUserCompNames.forEach { language ->
                        Competency newComp = new Competency(language: language)
                        newUserCompetencies.add(newComp)
                    }
                    print "Data de nascimento: "
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    LocalDate newUserBirthdate = LocalDate.parse(scanner.nextLine() as String, format)
                    Candidate newCandidate = new Candidate(
                            name: newUser.name,
                            email: newUser.email,
                            description: newUser.description,
                            address: newUserAddress,
                            competencies: newUserCompetencies,
                            cpf: newUserCPF,
                            age: newUserAge,
                            birthdate: newUserBirthdate
                    )
                    this.candidateControl.saveUser(newCandidate)
                    break
                case 2:
                    print "CNPJ: "
                    String newUserCNPJ = scanner.nextLine()
//                    CNPJ newUserCNPJ = new CNPJ(
//                            number: scanner.nextLine()
//                    )
                    Company newCompany = new Company(
                            name: newUser.name,
                            email: newUser.email,
                            description: newUser.description,
                            address: newUserAddress,
                            cnpj: newUserCNPJ
                    )
                    companyControl.saveUser(newCompany)
                    break
                default:
                    println "Opção inválida"
            }
        }
    }

    void drawNewJobMenu() {
        this.companies.each {println it}
        print "Empresa (Id): "
        Integer newJobCompany = scanner.nextInt()
        scanner.nextLine()
        print "Descrição: "
        String newJobDescription = scanner.nextLine()
        println "Local: "
        String newJobLocale = scanner.nextLine()
        Job newJob = new Job(idCompany: newJobCompany, description: newJobDescription, location: newJobLocale)
        this.jobControl.saveJob(newJob)
    }

    private Address getNewUserAddress() {
        println "Insira seu endereço"
        print "País: "
        String newUserCountry = scanner.nextLine()
        print "Estado: "
        String newUserState = scanner.nextLine()
        print "Cidade: "
        String newUserCity = scanner.nextLine()
        print "Bairro: "
        String newUserDistrict = scanner.nextLine()
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
                complement: newUserComplement,
                city: newUserCity,
                district: newUserDistrict
        )
    }

    private User getNewUserGeneralInfo() {
        print "Nome: "
        String newUserName = scanner.nextLine()
        print "E-mail: "
        String newUserEmail = scanner.nextLine()
        println "Insira uma breve descrição sobre você"
        String newUserDescription = scanner.nextLine()

        return new User(
                name: newUserName,
                email: newUserEmail,
                description: newUserDescription
        )
    }
}
