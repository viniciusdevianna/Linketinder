import User from "../models/User"
import Candidate from "../models/Candidate"
import Company from "../models/Company"
import load_candidates from "../data/candidate-loader"

let candidates: Candidate[]
let companies: Company[]

function get_user_logged(): User | null {
    let user: string | null = localStorage.getItem("user")
    return user ? new User(JSON.parse(user)) : null
}

function load(): void {
    candidates = load_candidates()
    companies = initial_load_companies()
    let user: User | null = get_user_logged()
    if (!user) {
        window.location.href = "http://localhost:8080/templates/login.html"
    }

    updateUserList(candidates)
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

const logout = document.getElementById("logout") as HTMLAnchorElement
logout.onclick = (event) => {
    localStorage.removeItem("user")
}

load()
