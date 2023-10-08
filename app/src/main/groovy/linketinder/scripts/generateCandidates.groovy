package linketinder.scripts

import groovy.json.JsonBuilder

def nCandidates = 5
def candidateBuilder = new JsonBuilder()
def file = new File("data/candidates.json")
file.write("[")

nCandidates.times {
    def i = it
    candidateBuilder {
        name "Candidato $i"
        email "candidato${i}@gmail.com"
        description "Um candidato dedicado"
        address {
            country "Brasil"
            state "RJ"
            cep "${i.toString() * 8}"
            street "Rua $i"
            number i
            complement "AP 10$i"
        }
        competencies "Java, Groovy, Git, Github"
        cpf {
            number 13244636780
        }
        age 25 + i
        education "Superior Completo, "
        languages "Inglês, "
    }
    if (i < nCandidates - 1) {
        file << candidateBuilder.toPrettyString() + ",\n"
    } else {
        file << candidateBuilder.toPrettyString() + "]"
    }
}
