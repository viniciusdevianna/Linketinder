package data

import groovy.json.JsonSlurper
import model.Company
import model.util.Address
import model.util.CNPJ

class CompanyDAO {
    String path = "./data/companies.json"

    def read() {
        File database = new File(path)
        JsonSlurper parser = new JsonSlurper()
        def jsonObjects = parser.parse(database)
        def listOfCompanies = []
        jsonObjects.each {
            def cnpj = it["cnpj"] as CNPJ
            def address = it["address"] as Address
            def company = new Company(
                    name: it["name"],
                    email: it["email"],
                    description: it["description"],
                    address: address,
                    competencies: it["competencies"] as List,
                    cnpj: cnpj,
                    nOpenJobs: it["nOpenJobs"] as Integer,
                    nJobsFullfilled: it["nJobsFulfilled"] as Integer
            )
            listOfCompanies << company
        }

        return listOfCompanies
    }
}
