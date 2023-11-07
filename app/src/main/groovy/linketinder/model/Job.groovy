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
 > Vaga: $idJob
 Descrição: $description
 Local: ${location}
 Competências: ${competencies}
"""
    }
}
