package model

import model.util.CNPJ

class Company extends User{
    CNPJ cnpj

    @Override
    String toString() {
        return """ 
 > Empresa: $name | CNPJ: $cnpj
 Descrição: $description
"""
    }
}
