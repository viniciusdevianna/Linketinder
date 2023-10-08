package control

import data.UserDaoInterface
import model.User

class UserController {
    private UserDaoInterface userDao

    UserController(UserDaoInterface userDao) {
        this.userDao = userDao
    }

    def getAllUsers() {
        return userDao.getAll()
    }

    def saveUser(User newUser) {
        userDao.save(newUser)
    }

}
