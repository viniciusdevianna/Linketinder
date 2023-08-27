package data

import groovy.json.JsonSlurper
import model.Candidate
import model.util.Address
import model.util.CPF

class CandidateDAO {
    String path = "./data/candidates.json"

    def getAllCandidates() {
        File database = new File(path)
        JsonSlurper parser = new JsonSlurper()
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
                    competencies: it["competencies"] as List,
                    cpf: cpf,
                    age: it["age"] as Integer,
                    education: it["education"] as List,
                    languages: it["languages"] as List
            )
            listOfCandidates << candidate
        }

        return listOfCandidates
    }
}
