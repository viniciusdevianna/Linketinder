package model

import groovy.transform.ToString
import model.util.CPF

class Candidate extends User{
    CPF cpf
    Integer age
    List education
    List languages

    @Override
    String toString() {
        return """ 
 > Candidato: $name ($age) | CPF: $cpf
 Descrição: $description
 Formação: ${String.join(', ' , education)}
 Competências: ${String.join(', ', competencies)}
"""
    }
}
