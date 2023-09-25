import User from "../models/User"
import Candidate from "../models/Candidate"
import Company from "../models/Company"

let candidates: Candidate[]
let companies: Company[]

function get_user_logged(): User | null {
    let user: string | null = localStorage.getItem("user")
    return user ? new User(JSON.parse(user)) : null
}

function load(): void {
    let user: User | null = get_user_logged()
    if (!user) {
        window.location.href = "http://localhost:8080/templates/login.html"
    }

    candidates = initial_load_candidates()
    companies = initial_load_companies()
    updateUserList(candidates)
}

function initial_load_candidates(): Candidate[] {
    let pre_load: Candidate[] = []
    let stored: string = localStorage.getItem("candidates") || ""
    if (stored) {
        const saved_candidates = JSON.parse(stored)
        saved_candidates.forEach((candidate: Candidate) => {
            let newCandidate = new Candidate(candidate)
            pre_load.push(newCandidate)
        })
        return pre_load
    }

    for (let i = 0; i < 5; i++) {
        let candidate = new Candidate({
            id: i,
            name: `Candidato ${i}`,
            email: `candidato${i}@gmail.com`,
            competencies: ["TypeScript", "Java", "Groovy"],
            age: 20 + i
        })
        pre_load.push(candidate)
    }
    localStorage.setItem("candidates", JSON.stringify(pre_load))
    return pre_load
}

function initial_load_companies(): Company[] {
    let stored: string = localStorage.getItem("companies") || ""
    if (stored) {
        return JSON.parse(stored)
    }

    let pre_load = []
    for (let i = 0; i < 5; i++) {
        let company = new Company({
            id: i + 5,
            name: `Empresa ${i}`,
            email: `empresa${i}@gmail.com`,
            competencies: ["TypeScript", "Java", "Groovy"]
        })
        pre_load.push(company)
    }
    localStorage.setItem("companies", JSON.stringify(pre_load))
    return pre_load
}

function updateUserList(users: User[]): void {
    const userList = document.getElementById("userList")

    if (userList) {
        userList.innerHTML = ""

        users.forEach((user: User) => {
            let li = document.createElement("li")
            li.className = "user-card"
            li.id = `userCard${user.id}`
            li.innerHTML = `<h3>${user.name}</h3>`
            userList.appendChild(li)
        })
    }
}

load()