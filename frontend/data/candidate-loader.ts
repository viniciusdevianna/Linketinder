import Candidate from "../models/Candidate"

export default function load_candidates(): Candidate[] {
    let pre_load: Candidate[] = []
    let stored: string = localStorage.getItem("candidates") || ""
    if (stored) {
        const saved_candidates = JSON.parse(stored)
        saved_candidates.forEach((candidate: Candidate) => {
            let newCandidate = new Candidate(candidate)
            pre_load.push(newCandidate)
        })
        return pre_load
    }

    for (let i = 0; i < 5; i++) {
        let candidate = new Candidate({
            id: i,
            name: `Candidato ${i}`,
            email: `candidato${i}@gmail.com`,
            competencies: ["TypeScript", "Java", "Groovy"],
            age: 20 + i
        })
        pre_load.push(candidate)
    }
    localStorage.setItem("candidates", JSON.stringify(pre_load))
    return pre_load
}