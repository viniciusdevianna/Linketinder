package control

import data.UserDaoInterface
import model.Company
import model.User

class UserController {
    private UserDaoInterface userDao

    UserController(UserDaoInterface userDao) {
        this.userDao = userDao
    }

    def getAllUsers() {
        return userDao.read()
    }

    def saveUser(User newUser) {
        userDao.save(newUser)
    }

}
