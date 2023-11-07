package linketinder.view

import linketinder.control.UserController
import linketinder.model.Candidate
import linketinder.model.User
import linketinder.model.Address
import linketinder.model.Competency

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CandidateView extends UserView{
    private List<Candidate> candidates

    CandidateView(UserController userController, Scanner scanner) {
        super(scanner, userController)
        this.candidates = this.controller.getAllUsers() as List<Candidate>
    }

    void drawUsersMenu() {
        Integer option = 0
        this.candidates.each { println it }
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
                    this.drawDeleteUserMenu(candidate)
                    break
                case 3:
                    println "Voltando..."
                    break
                default:
                    println "Opção inválida"
            }
        }
    }

    void drawNewUserMenu() {
        User newCandidate = this.getNewUserGeneralInfo()
        Address newCandidateAddress = this.getNewUserAddress()

        print "CPF: "
        String newCandidateCPF = scanner.nextLine()
        print "Idade: "
        Integer newCandidateAge = scanner.nextInt()
        scanner.nextLine()
        println "Insira suas competências, separando cada item por vírgula"
        List newCandidateCompetencies = []
        List newCandidateCompNames = scanner.nextLine().split(",")
        newCandidateCompNames.forEach { language ->
            Competency newComp = new Competency(language: language)
            newCandidateCompetencies.add(newComp)
        }
        print "Data de nascimento: "
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        LocalDate newCandidateBirthdate = LocalDate.parse(scanner.nextLine() as String, format)
        Candidate newCandidateComplete = new Candidate(
                name: newCandidate.name,
                email: newCandidate.email,
                description: newCandidate.description,
                address: newCandidateAddress,
                competencies: newCandidateCompetencies,
                cpf: newCandidateCPF,
                age: newCandidateAge,
                birthdate: newCandidateBirthdate
        )
        this.controller.saveUser(newCandidateComplete)
    }

    void drawEditCandidateMenu(Candidate candidate) {
        User editedUser = this.getEditUserInfo(candidate)
        candidate.name = editedUser.name
        candidate.email = editedUser.email
        candidate.description = editedUser.description
        println "CPF: ${candidate.cpf}"
        String newUserCPF = scanner.nextLine()
        if (!newUserCPF.empty)  candidate.cpf = newUserCPF
        println "Insira suas competências, separando cada item por vírgula"
        println candidate.competencies
        List candidateCompetencies = []
        String newCompetenciesString = scanner.nextLine()
        if (!newCompetenciesString.empty) {
            List candidateCompNames = newCompetenciesString.split(",")
            candidateCompNames.forEach { language ->
                Competency newComp = new Competency(language: language)
                candidateCompetencies.add(newComp)
            }
            candidate.competencies = candidateCompetencies
        }

        this.controller.updateUser(candidate)
    }

}
