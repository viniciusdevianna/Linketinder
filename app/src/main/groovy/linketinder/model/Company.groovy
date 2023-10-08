package linketinder.model

import linketinder.model.util.CNPJ

class Company extends User{
    Integer idCompany
    // CNPJ cnpj
    String cnpj

    @Override
    String toString() {
        return """ 
 > (ID: $idCompany): $name | CNPJ: $cnpj
 Descrição: $description
"""
    }
}
