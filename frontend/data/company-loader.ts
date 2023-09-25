import Company from "../models/Company"

export default function loadCompanies(): Company[] {
    let pre_load: Company[] = []
    let stored: string = localStorage.getItem("companies") || ""
    if (stored) {
        const saved_companys = JSON.parse(stored)
        saved_companys.forEach((company: Company) => {
            let newCompany = new Company(company)
            pre_load.push(newCompany)
        })
        return pre_load
    }

    for (let i = 0; i < 5; i++) {
        let company = new Company({
            id: i,
            name: `Candidato ${i}`,
            email: `candidato${i}@gmail.com`,
            competencies: ["TypeScript", "Java", "Groovy"],
        })
        pre_load.push(company)
    }
    localStorage.setItem("companies", JSON.stringify(pre_load))
    return pre_load
}