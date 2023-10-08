package control

import data.UserDaoInterface
import model.User

class UserController {
    private UserDaoInterface userDao

    UserController(UserDaoInterface userDao) {
        this.userDao = userDao
    }

    List<User> getAllUsers() {
        return userDao.getAll()
    }

    void saveUser(User newUser) {
        userDao.save(newUser)
    }

    void deleteUser(User user) {
        userDao.delete(user)
    }

    void updateUser(User user) {
        userDao.update(user)
    }

}
