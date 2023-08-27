package model

import model.util.CNPJ

class Company extends User{
    CNPJ cnpj
    Integer nOpenJobs
    Integer nJobsFullfilled

    @Override
    String toString() {
        return """ 
 > Empresa: $name | CNPJ: $cnpj
 Descrição: $description
 Competências Desejadas: ${String.join(', ', competencies)}
"""
    }
}
