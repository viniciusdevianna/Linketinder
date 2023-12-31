package linketinder.data

import groovy.sql.Sql
import linketinder.data.interfaces.IDatabaseConnector
import linketinder.data.interfaces.IUserDao
import linketinder.model.Candidate
import linketinder.model.User
import linketinder.model.Address
// import model.util.CPF

class CandidateDAO implements IUserDao{
    private CompetencyDAO competencyDAO
    private IDatabaseConnector databaseConnector

    CandidateDAO(CompetencyDAO competencyDAO, IDatabaseConnector databaseConnector) {
        this.competencyDAO = competencyDAO
        this.databaseConnector = databaseConnector
    }

    List<Candidate> getAll() {
        List<Candidate> allCandidates = []
        try {
            this.databaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        """SELECT a.id_candidate, 
                                  a.cpf,
                                  DATE_PART('YEAR',age(a.birthdate)) as age,
                                  a.birthdate,
                                  b.id_user,
                                  b.password,
                                  b.name,
                                  b.email,
                                  b.description,
                                  c.state,
                                  c.city,
                                  c.district,
                                  c.street,
                                  c.number,
                                  c.complement,
                                  c.cep,
                                  d.long as country
                            FROM candidates a,
                                 users b,
                                 addresses c,
                                 countries d,
                                 user_address e
                            WHERE a.id_user = b.id_user AND
                                  a.id_user = e.id_user AND
                                  e.id_address = c.id_address AND
                                  c.country = d.id_country"""
                ) {
//                    CPF cpf = new CPF()
//                    cpf.number = it.cpf
                    Candidate candidate = new Candidate(
                            name: it.name,
                            idCandidate: it.id_candidate,
                            idUser: it.id_user,
                            password: it.password,
                            birthdate: it.birthdate.toLocalDate(),
                            description: it.description,
                            email: it.email,
                            age: it.age,
                            cpf: it.cpf
                    )
                    Address address = new Address(
                            country: it.country,
                            state: it.state,
                            cep: it.cep,
                            city: it.city,
                            district: it.district,
                            number: it.number,
                            complement: it.complement
                    )
                    candidate.address = address
                    candidate.competencies = this.competencyDAO.getCompetencyByCandidate(candidate.idCandidate)
                    allCandidates.add(candidate)
                }
            }
        } catch (Exception e) {
            println e
        }

        return allCandidates
    }

    void save(User newUser) {
        try {
            Candidate newCandidate = newUser as Candidate
            Integer newCandidateId = 0
            this.databaseConnector.executeInstance {
                Sql sql ->
                    sql.withTransaction {
                        sql.execute("INSERT INTO users (name, password, email, description) VALUES (?, ?, ?, ?)",
                                newCandidate.name, 'Default1!', newCandidate.email, newCandidate.description)
                        sql.execute("INSERT INTO candidates (id_user, cpf, birthdate) VALUES ((SELECT currval(pg_get_serial_sequence('users', 'id_user'))), ?, ?)",
                                newCandidate.cpf, Sql.DATE(newCandidate.birthdate))
                        sql.execute("INSERT INTO addresses (country, state, city, district, street, number, complement, cep) VALUES ((SELECT id_country FROM countries WHERE long = ?), ?, ?, ?, ?, ?, ?, ?)",
                                newCandidate.address.country, newCandidate.address.state, newCandidate.address.city, newCandidate.address.district, newCandidate.address.street, newCandidate.address.number, newCandidate.address.complement, newCandidate.address.cep)
                        sql.execute("INSERT INTO user_address (id_user, id_address) VALUES ((SELECT currval(pg_get_serial_sequence('users', 'id_user'))), (SELECT currval(pg_get_serial_sequence('addresses', 'id_address'))))")
                        newCandidateId = sql.firstRow("SELECT currval(pg_get_serial_sequence('candidates', 'id_candidate'))").values()[0] as Integer
                    }
            }
            this.competencyDAO.addCandidateCompetencies(newCandidateId, newCandidate.competencies)
        } catch (Exception e) {
            println e
        }

    }

    void delete(User user) {
        try {
            this.databaseConnector.executeInstance {
                Sql sql -> sql.execute(
                        "DELETE FROM users WHERE id_user = ${user.idUser}"
                )
            }
        } catch (Exception e) {
            println e
        }
    }

    void update(User user) {
        try {
            Candidate candidate = user as Candidate
            this.databaseConnector.executeInstance {
                Sql sql -> sql.withTransaction {
                    sql.executeUpdate("UPDATE users SET name = ?, password = ?, email = ?, description = ? WHERE id_user = ?",
                            candidate.name, candidate.password, candidate.email, candidate.description, candidate.idUser)
                    sql.executeUpdate("UPDATE candidates SET cpf = ?, birthdate = ? WHERE id_user = ?",
                            candidate.cpf, Sql.DATE(candidate.birthdate), candidate.idUser)

                }
            }
            this.competencyDAO.updateCandidateCompetencies(candidate.idUser, candidate.competencies)
        } catch (Exception e) {
            println e
        }
    }

    Candidate getCandidateById(Integer id) {
        Candidate candidate = new Candidate()
        try {
            this.databaseConnector.executeInstance {
                Sql sql -> sql.execute(
                        """SELECT a.id_candidate, 
                                  a.cpf,
                                  DATE_PART('YEAR',age(a.birthdate)) as age,
                                  a.birthdate,
                                  b.id_user,
                                  b.password,
                                  b.name,
                                  b.email,
                                  b.description,
                                  c.state,
                                  c.city,
                                  c.district,
                                  c.street,
                                  c.number,
                                  c.complement,
                                  c.cep,
                                  d.long as country
                            FROM candidates a,
                                 users b,
                                 addresses c,
                                 countries d,
                                 user_address e
                            WHERE a.id_user = b.id_user AND
                                  a.id_user = e.id_user AND
                                  e.id_address = c.id_address AND
                                  c.country = d.id_country AND
                                  a.id_candidate = ${id}"""
                ) {
//                    CPF cpf = new CPF()
//                    cpf.number = it.cpf(
                    candidate.name = it.name
                    candidate.idCandidate = it.id_candidate
                    candidate.idUser = it.id_user
                    candidate.password =it.password
                    candidate.description = it.description
                    candidate.email = it.email
                    candidate.age = it.age
                    candidate.cpf = it.cpf
                    candidate.birthdate = it.birthdate.toLocalDate()
                    Address address = new Address(
                            country: it.country,
                            state: it.state,
                            cep: it.cep,
                            city: it.city,
                            district: it.district,
                            number: it.number,
                            complement: it.complement
                    )
                    candidate.address = address
                    candidate.competencies = this.competencyDAO.getCompetencyByCandidate(candidate.idCandidate)
                }
            }
        } catch (Exception e) {
            println e
        }

        return candidate
    }
}
