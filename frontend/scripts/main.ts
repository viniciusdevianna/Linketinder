import User from "../models/User"
import Candidate from "../models/Candidate"
import Company from "../models/Company"
import Job from "../models/Job"
import loadCandidates from "../data/candidate-loader"
import loadCompanies from "../data/company-loader"
import loadJobs from "../data/job-loader"
import Chart from "chart.js/auto"

let candidates: Candidate[]
let companies: Company[]
let jobs: Job[]

function getUserLogged(): User | null {
    let user: string | null = localStorage.getItem("user")
    if (user) {
        if (user.includes("age")) {
            return new Candidate(JSON.parse(user))
        }
        return new Company(JSON.parse(user))
    }
    return null
}

function load(): void {
    candidates = loadCandidates()
    companies = loadCompanies()
    jobs = loadJobs()
    let user: User | null = getUserLogged()
    if (!user) {
        window.location.href = "http://localhost:8080/templates/login.html"
    }

    populateMainUserCard(user!)
    populateCompetenciesCard(user!)

    if (user instanceof Company) {
        let canvas = document.getElementById("competenciesChart") as HTMLCanvasElement
        if (canvas) {
            let data = getCompetenciesData()
            createCompetenciesChart(canvas, data)
        }
        updateUserList(candidates)
    } else {
        document.getElementById("chartWrapper")!.style.display = "none"
        updateJobList(jobs)
    }

}

function updateUserList(users: User[]): void {
    const userList = document.getElementById("userList")

    if (userList) {
        userList.innerHTML = ""

        users.forEach((user: User) => {
            let li = document.createElement("li")
            li.className = "user-card"
            li.id = `userCard${user.id}`
            li.innerHTML = `<div class="user-item">
                                <h1>Candidato ${user.id}</h1>
                                <ul id="competencies-list">`
            user.competencies.forEach((competency) => {
                li.innerHTML += `<li> ${competency} </li>`
            })
            li.innerHTML += `</ul></div>`
            userList.appendChild(li)
        })
    }
}

function updateJobList(jobs: Job[]) {
    const userList = document.getElementById("userList")

    if (userList) {
        userList.innerHTML = ""

        jobs.forEach((job: Job) => {
            let li = document.createElement("li")
            li.className = "user-card"
            li.id = `userCard${job.id}`
            li.innerHTML = `<div class="user-item">
                                <h1>${job.titulo}</h1>
                                <h2>${job.descricao}</h2>
                                <ul id="competencies-list">`
            job.competencies.forEach((competency) => {
                li.innerHTML += `<li> ${competency} </li>`
            })
            li.innerHTML += `</ul></div>`
            userList.appendChild(li)
        })
    }
}

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

function getCompetenciesData(): { [key: string]: number } {
    let data: { [key: string]: number } = {}
    candidates.forEach((candidate) => {
        candidate.competencies.forEach((competency) => {
            if (!(competency in data)) {
                data[competency] = 0
            }
            data[competency] += 1
        })
    })

    console.log(data)
    return data
}

function createCompetenciesChart(canvas: HTMLCanvasElement, data: { [key: string]: number }): void {
    new Chart(canvas,
        {
            type: 'bar',
            data: {
                labels: Object.keys(data),
                datasets: [{
                    label: "Número de candidatos por competência",
                    data: Object.values(data)
                }]
            }
        })
}

const logout = document.getElementById("logout") as HTMLAnchorElement
logout.onclick = (event) => {
    localStorage.removeItem("user")
}

load()
