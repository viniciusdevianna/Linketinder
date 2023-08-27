package model

import groovy.transform.ToString
import model.util.CNPJ

@ToString
class Company extends User{
    CNPJ cnpj
    Integer nOpenJobs
    Integer nJobsFullfilled
}
