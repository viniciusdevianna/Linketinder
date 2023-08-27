import model.*

def address = new Address(
        country: "Brasil",
        state: "RJ",
        cep: "20540110",
        street: "Rua Tomas Coelho",
        number: 98,
        complement: "Ap 302"
)
def candidate = new Candidate(
        name: "Vinicius",
        email: "vinicius@gmail.com",
        address: address,
        description: "Um dev normal",
        competencies: ["Java", "Groovy"],
        cpf: new CPF("14677991731"),
        age: 30
)

println candidate.name
println candidate.cpf
println candidate.address.street + candidate.address.number