package scripts

import groovy.json.JsonBuilder
import model.Company
import model.util.Address
import model.util.CNPJ

def nCompanies = 5
def listOfCompanies = []

nCompanies.times {
    def address = new Address(
            country: "Brasil",
            state: "RJ",
            cep: "${it.toString() * 8}",
            street: "Rua $it",
            number: it,
            complement: "AP 10$it"
    )
    def competencies = ["Java", "Groovy", "Git", "Github"]
    def cnpj = new CNPJ(number: "58577114000189")
    def company = new Company(
            name: "Empresa $it",
            email: "contato@empresa${it}.com",
            description: "Uma empresa justa",
            address: address,
            competencies: competencies,
            cnpj: cnpj,
            nOpenJobs: it,
            nJobsFullfilled: it
    )
    listOfCompanies << company
}

def file = new File("../data/companies.json")
def builder = new JsonBuilder(listOfCompanies)

file.write builder.toPrettyString()


