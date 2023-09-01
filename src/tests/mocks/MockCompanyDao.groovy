package tests.mocks

import data.UserDaoInterface
import model.Company
import model.User

class MockCompanyDao implements UserDaoInterface{
    private List<Company> companiesArray = new ArrayList<>()

    List<Company> read() {
        return companiesArray
    }

    void save(User newCompany) {
        if (newCompany instanceof Company) companiesArray.add(newCompany)
    }
}
