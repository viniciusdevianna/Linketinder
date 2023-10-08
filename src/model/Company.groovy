package model

import model.util.CNPJ

class Company extends User{
    Integer idCompany
    // CNPJ cnpj
    String cnpj

    @Override
    String toString() {
        return """ 
 > Empresa: $name | CNPJ: $cnpj
 Descrição: $description
"""
    }
}
