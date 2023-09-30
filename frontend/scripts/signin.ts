import Candidate from "../models/Candidate"
import Company from "../models/Company"
import User from "../models/User"
import loadCandidates from "../data/candidate-loader"
import loadCompanies from "../data/company-loader"
import Cleave from "cleave.js"
import 'cleave.js/dist/addons/cleave-phone.BR'

const form = document.getElementById("signinForm") as HTMLFormElement
const candidateTrigger = document.getElementById("candidateBtn")! as HTMLButtonElement
const companyTrigger = document.getElementById("companyBtn")! as HTMLButtonElement
const candidateInfo = document.getElementById("candidateInfo")
const companyInfo = document.getElementById("companyInfo")
const cpfInput = (document.getElementById("cpf") as HTMLInputElement)
const telInput = (document.getElementById("tel") as HTMLInputElement)
const cnpjInput = (document.getElementById("cnpj") as HTMLInputElement)
const cepInput = (document.getElementById("cep") as HTMLInputElement)

const regexFieldsCandidate = document.getElementsByClassName("regex-control-candidate")
const regexFieldsCompany = document.getElementsByClassName("regex-control-company")
const regexFieldsGeneral = document.getElementsByClassName("regex-control")
const validationMessages = document.getElementsByClassName("validation-message")

const regexFields = [...regexFieldsGeneral, ...regexFieldsCandidate, ...regexFieldsCompany]

new Cleave(telInput, {
    phone: true,
    phoneRegionCode: 'BR'
})

new Cleave(cpfInput, {
    blocks: [3, 3, 3, 2],
    delimiters: [".", ".", "-"]
})

new Cleave(cnpjInput, {
    blocks: [2, 3, 3, 4, 2],
    delimiters: [".", ".", "/", "-"]
})

new Cleave(cepInput, {
    blocks: [2, 3, 3],
    delimiters: [".", "-"]
})

const usernameRegex = /^\S+$/
const nameRegex = /^([À-úA-Za-z]+\s?)+$/
const emailRegex = /^[\w._%+-]+@[\w.-]+\.[a-zA-Z]{2,4}$/
const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%!^&*])\S{8,50}$/
const competenciesRegex = /^(\S+)$|^(\S+,?)+$/
const cpfRegex = /^(\d{3}\.){2}\d{3}-\d{2}$/
const telRegex = /^\d{2} \d{5} \d{4}$/
const linkedinRegex = /^linkedin\.com\/in\/[\w-]+\/$/
const githubRegex = /^github\.com\/[\w-]+$/
const cnpjRegex = /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/
const cepRegex = /^\d{2}\.\d{3}-\d{3}$/
const siteRegex = /^(https:\/\/www\.|http:\/\/www\.|www\.|https:\/\/|http:\/\/)?(?!www\.[a-zA-Z]*)([a-zA-Z]{2,})(\.[a-zA-Z]{2,})+([a-zA-Z]{2,})?(\/[a-zA-Z0-9]*)*$/

const regexes = [usernameRegex,
    nameRegex,
    emailRegex,
    competenciesRegex,
    passwordRegex,
    cpfRegex,
    telRegex,
    linkedinRegex,
    githubRegex,
    cnpjRegex,
    cepRegex,
    siteRegex
]

let fieldRegexTable: { [key: string]: RegExp } = {}
let validationMsgTable: { [key: string]: HTMLParagraphElement } = {}

for (let i = 0; i < regexFields.length; i++) {
    let element = regexFields[i] as HTMLInputElement
    let message = validationMessages[i] as HTMLParagraphElement
    fieldRegexTable[element.name] = regexes[i]
    validationMsgTable[element.name] = message
}

let typeOfUser = "Candidate"

const okBorder = "2px solid green"
const notOkBorder = "2px solid red"

const fieldCheck = (
    regex: RegExp,
    field: HTMLInputElement,
    isOk: (test: boolean, field: HTMLInputElement) => boolean | void
) => isOk(regex.test(field.value), field)

const colorBorder = (regex: RegExp, field: HTMLInputElement) => {
    fieldCheck(regex, field, function (test, field) {
        if (test) {
            field.style.border = okBorder
        } else {
            field.style.border = notOkBorder
        }
    })
}

const showMessage = (regex: RegExp, field: HTMLInputElement) => {
    fieldCheck(regex, field, function (test, field) {
        if (test) {
            validationMsgTable[field.name].style.display = "none"
        } else {
            validationMsgTable[field.name].style.display = "flex"
        }
    })
}

const canSubmitField = (regex: RegExp, field: HTMLInputElement) => {
    return fieldCheck(regex, field, (test) => test)
}

candidateTrigger.onclick = (e) => {
    if (candidateInfo && companyInfo) {
        candidateTrigger.disabled = true
        companyTrigger.disabled = false
        typeOfUser = candidateTrigger.value
        candidateInfo.style.display = "flex"
        companyInfo.style.display = "none"
    }
}

companyTrigger.onclick = (e) => {
    if (candidateInfo && companyInfo) {
        candidateTrigger.disabled = false
        companyTrigger.disabled = true
        typeOfUser = companyTrigger.value
        candidateInfo.style.display = "none"
        companyInfo.style.display = "flex"
    }
}

for (let element of regexFields) {
    let field = element as HTMLInputElement
    field.onkeyup = (e) => colorBorder(fieldRegexTable[field.name], field)
    field.onblur = (e) => showMessage(fieldRegexTable[field.name], field)
}

if (form) {
    form.addEventListener("submit", function (event) {
        event.preventDefault()

        if (typeOfUser === "Candidate") {
            let isFormOk = Array.from(regexFieldsCandidate).every((element) => {
                let field = element as HTMLInputElement
                return canSubmitField(fieldRegexTable[field.name], field)
            }) && Array.from(regexFieldsGeneral).every((element) => {
                let field = element as HTMLInputElement
                return canSubmitField(fieldRegexTable[field.name], field)
            })

            if (!isFormOk) {
                alert("Um ou mais campos estão incorretos")
            } else {
                let data = new FormData(form)
                let listOfCompetencies: string[] = (regexFields[3] as HTMLInputElement).value.split(",")
                let userList: User[] = []

                userList = loadCandidates()

                let candidate = new Candidate({
                    id: userList.length,
                    username: data.get("username") as string,
                    password: data.get("password") as string,
                    name: data.get("name") as string,
                    email: data.get("email") as string,
                    competencies: listOfCompetencies,
                    age: data.get("age") as unknown as number,
                    cpf: data.get("tel") as string,
                    tel: data.get("tel") as string,
                    linkedin: data.get("linkedin") as string,
                    github: data.get("github") as string
                })

                userList.push(candidate)
                localStorage.setItem("candidates", JSON.stringify(userList))
                localStorage.setItem("user", JSON.stringify(candidate))
                window.location.href = "http://localhost:8080/"
            }
        } else {
            let isFormOk = Array.from(regexFieldsCompany).every((element) => {
                let field = element as HTMLInputElement
                return canSubmitField(fieldRegexTable[field.name], field)
            }) && Array.from(regexFieldsGeneral).every((element) => {
                let field = element as HTMLInputElement
                return canSubmitField(fieldRegexTable[field.name], field)
            })

            if (!isFormOk) {
                alert("Um ou mais campos estão incorretos")
            } else {
                let data = new FormData(form)
                let listOfCompetencies: string[] = (regexFields[3] as HTMLInputElement).value.split(",")
                let userList: User[] = []

                userList = loadCompanies()

                let company = new Company({
                    id: userList.length,
                    username: data.get("username") as string,
                    password: data.get("password") as string,
                    name: data.get("name") as string,
                    email: data.get("email") as string,
                    competencies: listOfCompetencies,
                    cnpj: data.get("cnpj") as string,
                    cep: data.get("cep") as string,
                    site: data.get("site") as string
                })

                userList.push(company)
                localStorage.setItem("companies", JSON.stringify(userList))
                localStorage.setItem("user", JSON.stringify(company))
                window.location.href = "http://localhost:8080/"
            }
        }
    })
}