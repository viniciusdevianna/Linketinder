package data

import groovy.sql.Sql
import model.Candidate
import model.User
import model.util.Address
import model.util.CPF

class CandidateDAO implements UserDaoInterface{

    List<Candidate> read() {
        List<Candidate> allCandidates = []
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        """SELECT a.id_candidate, 
                                  a.cpf,
                                  DATE_PART('YEAR',age(a.birthdate)) as age,
                                  b.id_user,
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
                    candidate.competencies = []
                    allCandidates.add(candidate)
                }
            }
        } catch (Exception e) {
            println e
        }

        return allCandidates
    }

    void save(User newCandidate) {
        DatabaseConnector.executeInstance {
            Sql sql ->
                sql.withTransaction {
                    sql.execute("INSERT INTO users (name, password, email, description) VALUES (?, ?, ?, ?) RETURNING id_user",
                            newCandidate.name, 'Default1!', newCandidate.email, newCandidate.description)
                    sql.execute("INSERT INTO candidates (id_user, cpf, birthdate) VALUES ((SELECT currval(pg_get_serial_sequence('users', 'id_user'))), ?, ?)",
                                newCandidate.cpf, Sql.DATE(newCandidate.birthdate))
                    sql.execute("INSERT INTO addresses (country, state, city, district, street, number, complement, cep) VALUES ((SELECT id_country FROM countries WHERE long = ?), ?, ?, ?, ?, ?, ?, ?)",
                            newCandidate.address.country, newCandidate.address.state, newCandidate.address.city, newCandidate.address.district, newCandidate.address.street, newCandidate.address.number, newCandidate.address.complement, newCandidate.address.cep)
                    sql.execute("INSERT INTO user_address (id_user, id_address) VALUES ((SELECT currval(pg_get_serial_sequence('users', 'id_user'))), (SELECT currval(pg_get_serial_sequence('addresses', 'id_address'))))")
                }
        }
    }
}
