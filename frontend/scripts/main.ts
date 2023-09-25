import User from "../models/User"
import Candidate from "../models/Candidate"
import Company from "../models/Company"
import loadCandidates from "../data/candidate-loader"

let candidates: Candidate[]
let companies: Company[]

function getUserLogged(): User | null {
    let user: string | null = localStorage.getItem("user")
    return user ? new User(JSON.parse(user)) : null
}

function load(): void {
    candidates = loadCandidates()
    // companies = initialLoadCompanies()
    let user: User | null = getUserLogged()
    if (!user) {
        window.location.href = "http://localhost:8080/templates/login.html"
    } else {
        populateMainUserCard(user)
        populateCompetenciesCard(user)
    }

    // updateUserList(candidates)
}

function initialLoadCompanies(): Company[] {
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

// function updateUserList(users: User[]): void {
//     const userList = document.getElementById("userList")

//     if (userList) {
//         userList.innerHTML = ""

//         users.forEach((user: User) => {
//             let li = document.createElement("li")
//             li.className = "user-card"
//             li.id = `userCard${user.id}`
//             li.innerHTML = `<h3>${user.name}</h3>`
//             userList.appendChild(li)
//         })
//     }
// }

function populateMainUserCard(user: User): void {
    const userFullName = document.getElementById("userFullName")!
    const userUsername = document.getElementById("userUsername")!
    const userEmail = document.getElementById("userEmail")!
    const userAge = document.getElementById("userAge")!

    if (user) {
        userFullName.innerHTML = user.name
        userUsername.innerHTML = `@${user.username}`
        userEmail.innerHTML = user.email
    }

    if (user instanceof Candidate) {
        userAge.innerHTML = user.age.toString()
    }
}

function populateCompetenciesCard(user: User): void {
    const competenciesList = document.getElementById("userCompetencies")
    user.competencies.forEach((competency) => {
        let li = document.createElement("li")
        li.className = "competency-item"
        li.innerHTML = competency
        competenciesList?.appendChild(li)
    })
}

const logout = document.getElementById("logout") as HTMLAnchorElement
logout.onclick = (event) => {
    localStorage.removeItem("user")
}

load()
