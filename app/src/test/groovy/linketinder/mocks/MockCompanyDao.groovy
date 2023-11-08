package linketinder.mocks

import linketinder.dao.interfaces.UserDaoInterface
import linketinder.model.Company
import linketinder.model.User

class MockCompanyDao implements UserDaoInterface{
    private List<Company> companiesArray = new ArrayList<>()

    List<Company> getAll() {
        return companiesArray
    }

    void save(User newCompany) {
        if (newCompany instanceof Company) companiesArray.add(newCompany)
    }

    void delete(User user) {
        if (user instanceof Company) companiesArray -= user
    }

    void update(User user) {
        if (user instanceof Company) {
            User userToUpdate = companiesArray.find {user.idUser == it.idUser }
            companiesArray[companiesArray.indexOf(userToUpdate)] = user
        }
    }
}
