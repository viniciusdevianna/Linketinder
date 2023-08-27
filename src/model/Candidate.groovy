package model

import groovy.transform.ToString
import model.util.CPF

@ToString
class Candidate extends User{
    CPF cpf
    Integer age
    List education
    List languages
}
