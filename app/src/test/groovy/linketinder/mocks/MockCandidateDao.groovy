package linketinder.mocks

import linketinder.dao.interfaces.UserDaoInterface
import linketinder.model.Candidate
import linketinder.model.User

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
        if (user instanceof Candidate) {
            User userToUpdate = candidatesArray.find {user.idUser == it.idUser }
            candidatesArray[candidatesArray.indexOf(userToUpdate)] = user
        }
    }
}
