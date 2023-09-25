import User from "./User"

export default class Candidate extends User {
    age: number

    constructor({ id = 0, name = "default", email = "default@gmail.com", competencies = [""], age = 0 } = {}) {
        super({ id, name, email, competencies })
        this.age = age
    }
}