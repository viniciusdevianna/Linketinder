package linketinder.control

import linketinder.dao.interfaces.IUserDao
import linketinder.model.User

class UserController {
    private IUserDao userDao

    UserController(IUserDao userDao) {
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
