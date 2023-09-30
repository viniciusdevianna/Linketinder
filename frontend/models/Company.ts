import User from "./User"

export default class Company extends User {
    cnpj: string
    cep: string
    site: string

    constructor({ id = 0,
        username = "default",
        password = "default",
        name = "default",
        email = "default@gmail.com",
        competencies = [""],
        cnpj = "",
        cep = "",
        site = "" } = {}) {
        super({ id, username, password, name, email, competencies })
        this.cnpj = cnpj
        this.cep = cep
        this.site = site
    }
}