export default class Job {
    id: number
    idEmpresa: number
    titulo: string
    descricao: string
    competencies: string[]

    constructor({ id = 0, idEmpresa = 0, titulo = "default", descricao = "default", competencies = [""] } = {}) {
        this.id = id
        this.idEmpresa = idEmpresa
        this.titulo = titulo
        this.descricao = descricao
        this.competencies = competencies
    }
}