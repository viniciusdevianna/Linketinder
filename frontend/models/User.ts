export default class User {
    id: number
    username: string
    password: string
    name: string
    email: string
    competencies: string[]

    constructor({ id = 0, username = "default", password = "default", name = "default", email = "default@gmail.com", competencies = [""] } = {}) {
        this.id = id
        this.username = username
        this.password = password
        this.name = name
        this.email = email
        this.competencies = competencies
    }
}