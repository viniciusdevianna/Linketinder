package linketinder

import linketinder.control.UserController
import linketinder.model.Candidate
import linketinder.model.Company
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import linketinder.mocks.MockCandidateDao
import linketinder.mocks.MockCompanyDao

class UserControllerTest {
    private UserController userController

    @Test
    void testSavingNewCandidate() {
        // Given
        userController = new UserController(new MockCandidateDao())
        Candidate candidate = new Candidate()

        // When
        userController.saveUser(candidate)
        List expected = [candidate]

        // Then
        Assertions.assertEquals(expected, userController.getAllUsers())
    }

    @Test
    void testSavingNewCompany() {
        // Given
        userController = new UserController(new MockCompanyDao())
        Company company = new Company()

        // When
        userController.saveUser(company)
        List expected = [company]

        // Then
        Assertions.assertEquals(expected, userController.getAllUsers())
    }
}
