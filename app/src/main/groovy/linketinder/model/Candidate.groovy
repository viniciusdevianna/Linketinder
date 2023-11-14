package linketinder.model


import java.time.LocalDate

class Candidate extends User{
    LocalDate birthdate
    Integer idCandidate
//    CPF cpf
    String cpf
    Integer age
    List<Competency> competencies

    @Override
    String toString() {
        return """ 
 > (Id: $idCandidate): $name ($age) | CPF: $cpf
 Descrição: $description
 Competências: ${competencies}
"""
    }

    Map<String, String> toMap() {
        return [
                "idUser": this.idUser.toString(),
                "idCandidate": this.idCandidate.toString(),
                "name": this.name,
                "email": this.email,
                "description": this.description,
                "address": this.address.toString(),
                "birthdate": this.birthdate.toString(),
                "agr": this.age.toString(),
                "competencies": this.competencies.toString()
        ]
    }
}
