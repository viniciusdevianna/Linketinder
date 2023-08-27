package control

import data.CandidateDAO
import data.CompanyDAO
import model.Candidate

class UserController {
    CandidateDAO candidateDAO = new CandidateDAO()
    CompanyDAO companyDAO = new CompanyDAO()

    def getAllCandidates() {
        return candidateDAO.read()
    }

    def getAllCompanies() {
        return companyDAO.read()
    }

    def saveCandidate(Candidate newCandidate) {
        candidateDAO.save(newCandidate)
    }
}
