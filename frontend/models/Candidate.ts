import User from "./User"

export default class Candidate extends User {
    age: number

    constructor({ id = 0, username = "default", password = "default", name = "default", email = "default@gmail.com", competencies = [""], age = 0 } = {}) {
        super({ id, username, password, name, email, competencies })
        this.age = age
    }
}