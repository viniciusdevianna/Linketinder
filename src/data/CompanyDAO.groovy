package data

//TODO generalizar o acesso a usuário

import groovy.sql.Sql
import model.Company
import model.User
import model.util.Address
// import model.util.CNPJ

class CompanyDAO implements UserDaoInterface{

    List<Company> read() {
        List<Company> allCompanies = []
        try {
            DatabaseConnector.executeInstance {
                Sql sql -> sql.eachRow(
                        """SELECT a.id_company, 
                                  a.cnpj,
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
                            FROM companies a,
                                 users b,
                                 addresses c,
                                 countries d,
                                 user_address e
                            WHERE a.id_user = b.id_user AND
                                  a.id_user = e.id_user AND
                                  e.id_address = c.id_address AND
                                  c.country = d.id_country"""
                ) {
//                   CNPJ cnpj = new CNPJ()
//                    cnpj.number = it.cnpj
                    Company company = new Company(
                            name: it.name,
                            idCompany: it.id_company,
                            idUser: it.id_user,
                            description: it.description,
                            email: it.email,
                            cnpj: it.cnpj
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
                    company.address = address
                    allCompanies.add(company)
                }
            }
        } catch (Exception e) {
            println e
        }

        return allCompanies
    }

    void save(User newCompany) {
        DatabaseConnector.executeInstance {
            Sql sql ->
                sql.withTransaction {
                    sql.execute("INSERT INTO users (name, password, email, description) VALUES (?, ?, ?, ?)",
                            newCompany.name, 'Default1!', newCompany.email, newCompany.description)
                    sql.execute("INSERT INTO companies (id_user, cnpj) VALUES ((SELECT currval(pg_get_serial_sequence('users', 'id_user'))), ?)",
                            newCompany.cnpj)
                    sql.execute("INSERT INTO addresses (country, state, city, district, street, number, complement, cep) VALUES ((SELECT id_country FROM countries WHERE long = ?), ?, ?, ?, ?, ?, ?, ?)",
                            newCompany.address.country, newCompany.address.state, newCompany.address.city, newCompany.address.district, newCompany.address.street, newCompany.address.number, newCompany.address.complement, newCompany.address.cep)
                    sql.execute("INSERT INTO user_address (id_user, id_address) VALUES ((SELECT currval(pg_get_serial_sequence('users', 'id_user'))), (SELECT currval(pg_get_serial_sequence('addresses', 'id_address'))))")
                }
        }
    }
}
