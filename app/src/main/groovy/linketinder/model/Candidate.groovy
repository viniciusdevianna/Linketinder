package linketinder.model

import linketinder.model.util.CPF
import linketinder.model.util.Competency

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
}
