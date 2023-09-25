import load_candidates from "../data/candidate-loader"

const form = document.getElementById("loginForm")
const signin = document.getElementById("signinLink")

if (form) {
    form.addEventListener("submit", function (event) {
        event.preventDefault()
        let username = (document.getElementById("username") as HTMLInputElement).value
        let password = (document.getElementById("password") as HTMLInputElement).value

        let userList = load_candidates()
        let user = userList.filter((user) => username === user.username && password === user.password)
        if (!user[0]) {
            alert("Usuário ou senha incorretos")
        } else {
            localStorage.setItem("user", JSON.stringify(user[0]))
            window.location.href = "http://localhost:8080/"
        }
    })
}

if (signin) {
    signin.onclick = (e) => [
        window.location.href = "http://localhost:8080/templates/signin.html"
    ]
}