import Candidate from "../models/Candidate"
import Company from "../models/Company"
import User from "../models/User"
import loadCandidates from "../data/candidate-loader"
import loadCompanies from "../data/company-loader"

const form = document.getElementById("signinForm")
const candidateTrigger = document.getElementById("candidateBtn")! as HTMLButtonElement
const companyTrigger = document.getElementById("companyBtn")! as HTMLButtonElement

let typeOfUser = "Candidate"

candidateTrigger.onclick = (event) => {
    const ageInfo = document.getElementById("ageInfo")
    if (ageInfo) {
        ageInfo.style.display = "flex"
        candidateTrigger.disabled = true
        companyTrigger.disabled = false
        typeOfUser = candidateTrigger.value
    }
}

companyTrigger.onclick = (event) => {
    const ageInfo = document.getElementById("ageInfo")
    if (ageInfo) {
        ageInfo.style.display = "none"
        candidateTrigger.disabled = false
        companyTrigger.disabled = true
        typeOfUser = companyTrigger.value
    }
}

if (form) {
    form.addEventListener("submit", function (event) {
        event.preventDefault()
        let username = (document.getElementById("username") as HTMLInputElement).value
        let password = (document.getElementById("password") as HTMLInputElement).value
        let name = (document.getElementById("name") as HTMLInputElement).value
        let email = (document.getElementById("email") as HTMLInputElement).value
        let competencies = (document.getElementById("competencies") as HTMLInputElement).value

        competencies = competencies.replace(/\s\g/, "")
        let listOfCompetencies: string[] = competencies.split(",")
        let userList: User[] = []

        if (typeOfUser === "Candidate") {
            let age = Number((document.getElementById("age") as HTMLInputElement).value)
            userList = loadCandidates()

            let candidate = new Candidate({
                id: userList.length,
                username: username,
                password: password,
                name: name,
                email: email,
                competencies: listOfCompetencies,
                age: age
            })

            userList.push(candidate)
            localStorage.setItem("candidates", JSON.stringify(userList))
            localStorage.setItem("user", JSON.stringify(candidate))
        } else {
            userList = loadCompanies()

            let company = new Company({
                id: userList.length,
                username: username,
                password: password,
                name: name,
                email: email,
                competencies: listOfCompetencies
            })

            userList.push(company)
            localStorage.setItem("companies", JSON.stringify(userList))
            localStorage.setItem("user", JSON.stringify(company))
        }


        window.location.href = "http://localhost:8080/"

    })
}