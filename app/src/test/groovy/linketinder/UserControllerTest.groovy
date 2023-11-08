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

    @Test
    void testDeletingCandidate() {
        // Given
        userController = new UserController(new MockCandidateDao())
        Candidate candidate1 = new Candidate()
        Candidate candidate2 = new Candidate()
        userController.saveUser(candidate1)
        userController.saveUser(candidate2)

        // When
        userController.deleteUser(candidate1)
        List expected = [candidate2]

        // Then
        Assertions.assertEquals(expected, userController.getAllUsers())
    }

    @Test
    void testDeletingCompany() {
        // Given
        userController = new UserController(new MockCompanyDao())
        Company company1 = new Company()
        Company company2 = new Company()
        userController.saveUser(company1)
        userController.saveUser(company2)

        // When
        userController.deleteUser(company1)
        List expected = [company2]

        // Then
        Assertions.assertEquals(expected, userController.getAllUsers())
    }

    @Test
    void testUpdatingCandidate() {
        // Given
        userController = new UserController(new MockCandidateDao())
        Candidate candidate = new Candidate(idUser: 1, name: "Teste")
        Candidate updatedCandidate = new Candidate(idUser: 1, name: "Novo")
        userController.saveUser(candidate)

        // When
        userController.updateUser(updatedCandidate)
        String expected = "Novo"

        // Then
        Assertions.assertEquals(expected, userController.getAllUsers()[0].name)
    }

    @Test
    void testUpdatingCompany() {
        // Given
        userController = new UserController(new MockCompanyDao())
        Company company = new Company(idUser: 1, name: "Teste")
        Company updatedCompany = new Company(idUser: 1, name: "Nova")
        userController.saveUser(company)

        // When
        userController.updateUser(updatedCompany)
        String expected = "Nova"

        // Then
        Assertions.assertEquals(expected, userController.getAllUsers()[0].name)
    }
}
