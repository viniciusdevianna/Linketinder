package linketinder.dao.interfaces

import linketinder.model.User

interface IUserDao {
    List<User> getAll()
    void save(User newUser)
    void delete(User user)
    void update(User user)
}