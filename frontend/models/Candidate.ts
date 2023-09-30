import User from "./User"

export default class Candidate extends User {
    age: number
    cpf: string
    tel: string
    linkedin: string
    github: string

    constructor({
        id = 0,
        username = "default",
        password = "default",
        name = "default",
        email = "default@gmail.com",
        competencies = [""],
        age = 0,
        cpf = "",
        tel = "",
        linkedin = "",
        github = "" } = {}) {
        super({ id, username, password, name, email, competencies })
        this.age = age
        this.cpf = cpf
        this.tel = tel
        this.linkedin = linkedin
        this.github = github
    }
}