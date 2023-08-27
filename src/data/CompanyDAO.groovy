package data

//TODO generalizar o acesso a usuário

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import model.Company
import model.util.Address
import model.util.CNPJ

class CompanyDAO {
    String path = "./data/companies.json"
    File database = new File(path)
    JsonSlurper parser = new JsonSlurper()
    JsonBuilder builder = new JsonBuilder()

    def read() {
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

    @SuppressWarnings('GroovyAssignabilityCheck')
    def save(Company newCompany) {
        builder {
            name newCompany.name
            email newCompany.email
            description newCompany.description
            address {
                country newCompany.address.country
                state newCompany.address.state
                cep newCompany.address.cep
                street newCompany.address.street
                number newCompany.address.number
                complement newCompany.address.complement
            }
            competencies newCompany.competencies
            cnpj {
                number newCompany.cnpj.number
            }
            nOpenJobs newCompany.nOpenJobs
            nJobsFulfilled newCompany.nJobsFullfilled
        }
        File temp = File.createTempFile(path, "temp")
        for (line in database) {
            if (line.contains("}]")) {
                line = line.replace("}]", "},")
            }
            temp << line + "\n"
        }
        temp << builder.toPrettyString()
        temp << "]"
        temp.renameTo(path)
    }
}
