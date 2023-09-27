package data

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import model.Candidate
import model.User
import model.util.Address
import model.util.CPF

class CandidateDAO implements UserDaoInterface{
    String path = "./data/candidates.json"
    File database = new File(path)
    JsonSlurper parser = new JsonSlurper()
    JsonBuilder builder = new JsonBuilder()

    CandidateDAO() {
        if (!database.exists()) {
            GroovyShell shell = new GroovyShell()
            shell.evaluate(new File("scripts/generateCandidates.groovy"))
        }
    }

    List<Candidate> read() {
        def jsonObjects = parser.parse(database)
        def listOfCandidates = []
        jsonObjects.each {
            def cpf = it["cpf"] as CPF
            def address = it["address"] as Address
            def candidate = new Candidate(
                    name: it["name"],
                    email: it["email"],
                    description: it["description"],
                    address: address,
                    competencies: (it["competencies"] as String).split(", "),
                    cpf: cpf,
                    age: it["age"] as Integer,
                    education: (it["education"] as String).split(", "),
                    languages: (it["languages"] as String).split(", ")
            )
            listOfCandidates << candidate
        }

        return listOfCandidates
    }

    @SuppressWarnings('GroovyAssignabilityCheck')
    void save(User newCandidate) {
        builder {
            name newCandidate.name
            email newCandidate.email
            description newCandidate.description
            address {
                country newCandidate.address.country
                state newCandidate.address.state
                cep newCandidate.address.cep
                street newCandidate.address.street
                number newCandidate.address.number
                complement newCandidate.address.complement
            }
            competencies String.join(", ", newCandidate.competencies)
            cpf {
                number newCandidate.cpf.number
            }
            age newCandidate.age
            education String.join(", ", newCandidate.education)
            languages String.join(", ", newCandidate.languages)
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
