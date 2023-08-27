package scripts

import groovy.json.JsonBuilder

def nCompanies = 5
def companyBuilder = new JsonBuilder()
def file = new File("../data/companies.json")
file.write("[")

nCompanies.times {
    def i = it
    companyBuilder {
        name "Empresa $i"
        email "contato@empresa${i}.com"
        description "Uma empresa justa"
        address {
            country "Brasil"
            state "RJ"
            cep "${i.toString() * 8}"
            street "Rua $i"
            number i
            complement "AP 10$i"
        }
        competencies "Java, Groovy, Git, Github"
        cnpj {
            number 58577114000189
        }
        nOpenJobs i
        nJobsFulfilled i
    }
    if (i < nCompanies - 1) {
        file << companyBuilder.toPrettyString() + ",\n"
    } else {
        file << companyBuilder.toPrettyString() + "]"
    }
}
