import User from "../models/User"
import loadCandidates from "./candidate-loader"
import loadCompanies from "./company-loader"

export default function loadUsers(): User[] {
    let pre_load: User[] = []
    let storedCandidates: string = localStorage.getItem("candidates") || ""
    let storedCompanies: string = localStorage.getItem("companies") || ""
    if (storedCandidates) {
        const savedCandidates = JSON.parse(storedCandidates)
        savedCandidates.forEach((user: User) => {
            let newUser = new User(user)
            pre_load.push(newUser)
        })
    }
    if (storedCompanies) {
        const savedCompanies = JSON.parse(storedCompanies)
        savedCompanies.forEach((user: User) => {
            let newUser = new User(user)
            pre_load.push(newUser)
        })
        return pre_load
    }

    let newCandidates = loadCandidates()
    let newCompanies = loadCompanies()
    pre_load = [...newCandidates, ...newCompanies]
    return pre_load
}