package linketinder.model

class Job {
    Integer idJob
    Integer idCompany
    String description
    String location
    List<Competency> competencies

    @Override
    String toString() {
        return """ 
 > Vaga: ${this.idJob}
 Descrição: ${this.description}
 Local: ${this.location}
 Competências: ${this.competencies}
"""
    }
}
