package linketinder.view

class MenuView {
    private final Scanner scanner
    private final CandidateView candidateView
    private final CompanyView companyView
    private final JobView jobView

    MenuView(Scanner scanner, CandidateView candidateView, CompanyView companyView, JobView jobView) {
        this.scanner = scanner
        this.candidateView = candidateView
        this.companyView = companyView
        this.jobView = jobView
    }

    void drawMainMenu() {
        Integer option = 0
        while (option != 5) {
            println "O que você deseja fazer?"
            println "1 - Listar candidatos"
            println "2 - Listar vagas"
            println "3 - Listar empresas"
            println "4 - Cadastrar novo usuário"
            println "5 - Sair"

            option = scanner.nextInt()
            switch (option) {
                case 1:
                    this.candidateView.drawUsersMenu()
                    break
                case 2:
                    this.jobView.drawJobsMenu()
                    break
                case 3:
                    this.companyView.drawUsersMenu()
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
            switch (newUserOption) {
                case 1:
                    this.candidateView.drawNewUserMenu()
                    break
                case 2:
                    this.companyView.drawNewUserMenu()
                    break
                default:
                    println "Opção inválida"
            }
        }
    }
}
