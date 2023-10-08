package data

import model.User

interface UserDaoInterface {
    List<User> getAll()
    void save(User newUser)
    void delete(User user)
    void update(User user)
}