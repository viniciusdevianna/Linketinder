package model

import model.util.CPF
import model.util.Competency

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
 > Candidato: $name ($age) | CPF: $cpf
 Descrição: $description
 Competências: ${competencies.forEach {it.language + ","}}
"""
    }
}
