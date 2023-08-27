import data.CandidateDAO

CandidateDAO dao = new CandidateDAO()
def allCandidates = dao.getAllCandidates()
for (candidate in allCandidates) {
    println candidate.name
}

