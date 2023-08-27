package model

import model.util.CNPJ

class Company extends User{
    CNPJ cnpj
    Integer nOpenJobs
    Integer nJobsFullfilled
}
