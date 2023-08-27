package scripts

import groovy.json.JsonBuilder
import model.Candidate
import model.util.Address
import model.util.CPF

def nCandidates = 5
def listOfCandidates = []

nCandidates.times {
    def address = new Address(
            country: "Brasil",
            state: "RJ",
            cep: "${it.toString() * 8}",
            street: "Rua $it",
            number: it,
            complement: "AP 10$it"
    )
    def competencies = ["Java", "Groovy", "Git", "Github"]
    def cpf = new CPF(number: "13244636780")
    def candidate = new Candidate(
            name: "Candidato $it",
            email: "candidato${it}@gmail.com",
            description: "Um candidato dedicado",
            address: address,
            competencies: competencies,
            cpf: cpf,
            age: 25 + it,
            education: ["Superior Completo",],
            languages: ["Inglês",]
    )
    listOfCandidates << candidate
}

def file = new File("../data/candidates.json")
def builder = new JsonBuilder(listOfCandidates)

file.write builder.toPrettyString()
