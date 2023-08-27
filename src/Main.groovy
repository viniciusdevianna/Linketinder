import data.CandidateDAO
import data.CompanyDAO

CandidateDAO dao = new CandidateDAO()
def allCandidates = dao.getAllCandidates()
for (candidate in allCandidates) {
    println candidate.name
}

CompanyDAO cdao = new CompanyDAO()
def allCompanies = cdao.getAllCompanies()
for (company in allCompanies) {
    println company.name
}

