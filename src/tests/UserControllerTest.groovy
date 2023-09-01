package tests

import control.UserController
import model.Candidate
import model.Company
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import tests.mocks.MockCandidateDao
import tests.mocks.MockCompanyDao

class UserControllerTest {
    private UserController userController

    @Test
    void testSavingNewCandidate() {
        userController = new UserController(new MockCandidateDao())
        Candidate candidate = new Candidate()

        userController.saveUser(candidate)
        List expected = [candidate]

        Assertions.assertEquals(expected, userController.getAllUsers())
    }

    @Test
    void testSavingNewCompany() {
        userController = new UserController(new MockCompanyDao())
        Company company = new Company()

        userController.saveUser(company)
        List expected = [company]

        Assertions.assertEquals(expected, userController.getAllUsers())
    }
}
