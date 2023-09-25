import Candidate from "../models/Candidate"
import load_candidates from "../data/candidate-loader"

const form = document.getElementById("signinForm")

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

        let userList = load_candidates()

        let candidate = new Candidate({
            id: userList.length,
            username: username,
            password: password,
            name: name,
            email: email,
            competencies: listOfCompetencies
        })

        userList.push(candidate)
        localStorage.setItem("candidates", JSON.stringify(userList))
        localStorage.setItem("user", JSON.stringify(candidate))
        window.location.href = "http://localhost:8080/"

    })
}