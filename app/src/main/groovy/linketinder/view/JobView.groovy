package linketinder.view

import linketinder.control.JobController
import linketinder.model.Competency
import linketinder.model.Job

class JobView {
    private final JobController jobControl
    private final Scanner scanner
    private List<Job> jobs

    JobView(JobController jobControl, Scanner scanner) {
        this.jobControl = jobControl
        this.scanner = scanner
        this.jobs = jobControl.getAllJobs()
    }

    void drawJobsMenu() {
        this.jobs.each { println it }
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
                    this.drawDeleteJobMenu(job)
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
        if (!newJobDescription.empty)  job.description = newJobDescription
        println "Local: ${job.location}"
        String newJobLocation = scanner.nextLine()
        if (!newJobLocation.empty)  job.location = newJobLocation
        println "Insira as competências exigidas, separando cada item por vírgula"
        println job.competencies
        List jobCompetencies = []
        String newCompetenciesString = scanner.nextLine()
        if (!newCompetenciesString.empty) {
            List jobCompNames = newCompetenciesString.split(",")
            jobCompNames.forEach { language ->
                Competency newComp = new Competency(language: language)
                jobCompetencies.add(newComp)
            }
            job.competencies = jobCompetencies
        }
        this.jobControl.updateJob(job)
    }

    void drawDeleteJobMenu(Job job) {
        println "Tem certeza que deseja deletar (S/N)?"
        String answer = scanner.nextLine().toUpperCase()
        if (answer == "S") {
            this.jobControl.deleteJob(job)
        }
    }

    void drawNewJobMenu() {
        print "Empresa (Id): "
        Integer newJobCompany = scanner.nextInt()
        scanner.nextLine()
        print "Descrição: "
        String newJobDescription = scanner.nextLine()
        println "Local: "
        String newJobLocale = scanner.nextLine()
        println "Insira as competências exigidas pela vaga, separando cada item por vírgula"
        List newJobCompetencies = []
        List newJobCompNames = scanner.nextLine().split(",")
        newJobCompNames.forEach { language ->
            Competency newComp = new Competency(language: language)
            newJobCompetencies.add(newComp)
        }
        Job newJob = new Job(idCompany: newJobCompany, description: newJobDescription, location: newJobLocale, competencies: newJobCompetencies)
        this.jobControl.saveJob(newJob)
    }
}
