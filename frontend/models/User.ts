export default class User {
    id: number
    name: string
    email: string
    competencies: string[]

    constructor({ id = 0, name = "default", email = "default@gmail.com", competencies = [""] } = {}) {
        this.id = id
        this.name = name
        this.email = email
        this.competencies = competencies
    }
}