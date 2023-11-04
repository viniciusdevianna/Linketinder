package linketinder.view

import linketinder.control.UserController
import linketinder.model.Address
import linketinder.model.User

abstract class UserView {
    protected final Scanner scanner
    protected final UserController controller

    UserView(Scanner scanner, UserController controller) {
        this.scanner = scanner
        this.controller = controller
    }

    abstract void drawUsersMenu()

    abstract void drawNewUserMenu()

    void drawDeleteUserMenu(User user) {
        println "Tem certeza que deseja deletar (S/N)?"
        String answer = scanner.nextLine().toUpperCase()
        if (answer == "S") {
            this.controller.deleteUser(user)
        }
    }

    User getEditUserInfo(User user) {
        println "Nome: ${user.name}"
        String newUserName = this.scanner.nextLine()
        if (newUserName.empty)  newUserName = user.name
        println "E-mail: ${user.email}"
        String newUserEmail = this.scanner.nextLine()
        if (newUserEmail.empty)  newUserEmail = user.email
        println "Descrição: ${user.description}"
        String newUserDescription = this.scanner.nextLine()
        if (newUserDescription.empty)  newUserDescription = user.description

        return new User(name: newUserName, email: newUserEmail, description: newUserDescription)
    }

    Address getNewUserAddress() {
        println "Insira seu endereço"
        print "País: "
        String newUserCountry = scanner.nextLine()
        print "Estado: "
        String newUserState = scanner.nextLine()
        print "Cidade: "
        String newUserCity = scanner.nextLine()
        print "Bairro: "
        String newUserDistrict = scanner.nextLine()
        print "Insira seu CEP apenas com números: "
        String newUserCep = scanner.nextLine()
        print "Rua: "
        String newUserStreet = scanner.nextLine()
        print "Número: "
        Integer newUserNumber = scanner.nextInt()
        scanner.nextLine()
        print "Complemento: "
        String newUserComplement = scanner.nextLine()
        return new Address(
                country: newUserCountry,
                state: newUserState,
                cep: newUserCep,
                street: newUserStreet,
                number: newUserNumber,
                complement: newUserComplement,
                city: newUserCity,
                district: newUserDistrict
        )
    }

    User getNewUserGeneralInfo() {
        print "Nome: "
        String newUserName = scanner.nextLine()
        print "E-mail: "
        String newUserEmail = scanner.nextLine()
        println "Insira uma breve descrição sobre você"
        String newUserDescription = scanner.nextLine()

        return new User(
                name: newUserName,
                email: newUserEmail,
                description: newUserDescription
        )
    }
}
