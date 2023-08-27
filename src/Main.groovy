import model.*
import model.util.Address
import model.util.CNPJ
import model.util.CPF

def address = new Address(
        country: "Brasil",
        state: "RJ",
        cep: "22222222",
        street: "Rua da Carochinha",
        number: 166,
        complement: "Ap 999"
)
def candidate = new Candidate(
        name: "Vinicius",
        email: "vinicius@gmail.com",
        address: address,
        description: "Um dev normal",
        competencies: ["Java", "Groovy"],
        cpf: new CPF(number: "13244636780"),
        age: 30,
        education: ["Formado em QI"],
        languages: ["Inglês", "Alemão"]
)
def company = new Company(
        name: "ZG",
        email: "zg@gmail.com",
        address: address,
        description: "A empresa do momento",
        competencies: ["Java, Groovy"],
        cnpj: new CNPJ(number: "58577114000189"),
        nOpenJobs: 0,
        nJobsFullfilled: 0
)

println candidate.name
println candidate.cpf
println company.name
println company.cnpj