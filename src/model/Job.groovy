package model

class Job {
    Integer idJob
    Integer idCompany
    String description
    String location

    @Override
    String toString() {
        return """ 
 > Vaga: $idJob
 Descrição: $description
 Local: ${location}
"""
    }
}
