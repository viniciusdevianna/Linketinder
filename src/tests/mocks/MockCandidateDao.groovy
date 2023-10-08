package tests.mocks

import data.UserDaoInterface
import model.Candidate
import model.User

class MockCandidateDao implements UserDaoInterface{
    private List<Candidate> candidatesArray = new ArrayList<>()

    List<Candidate> getAll() {
        return candidatesArray
    }

    void save(User newCandidate) {
        if (newCandidate instanceof Candidate) candidatesArray.add(newCandidate)
    }

    void delete(User user) {
        if (user instanceof Candidate) candidatesArray -= user
    }

    void update(User user) {
        if (user instanceof Candidate) candidatesArray[candidatesArray.indexOf(user)] = user
    }
}
