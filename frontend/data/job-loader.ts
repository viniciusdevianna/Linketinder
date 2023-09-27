import Job from "../models/Job";

export default function loadJobs(): Job[] {
    let pre_load: Job[] = []
    let stored: string = localStorage.getItem("jobs") || ""
    if (stored) {
        const saved_companys = JSON.parse(stored)
        saved_companys.forEach((job: Job) => {
            let newJob = new Job(job)
            pre_load.push(newJob)
        })
        return pre_load
    }

    for (let i = 0; i < 5; i++) {
        let job = new Job({
            id: i + 1,
            idEmpresa: i + 6,
            titulo: `Ótima vaga para júnior`,
            descricao: `Esta é uma vaga na melhor empresa da região`,
            competencies: ["TypeScript", "Java", "Groovy"],
        })
        pre_load.push(job)
    }
    localStorage.setItem("jobs", JSON.stringify(pre_load))
    return pre_load
}