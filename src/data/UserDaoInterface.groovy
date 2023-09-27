package data

import model.User

interface UserDaoInterface {
    List<User> read()
    void save(User newUser)
}