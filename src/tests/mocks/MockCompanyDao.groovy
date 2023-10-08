package tests.mocks

import data.UserDaoInterface
import model.Company
import model.User

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
        if (user instanceof Company) companiesArray[candidatesArray.indexOf(user)] = user
    }
}
