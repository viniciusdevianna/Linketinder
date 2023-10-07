package data

import groovy.sql.Sql
import org.apache.groovy.internal.util.Function

class DatabaseConnector {
    private static Map params = [
            url: "jdbc:postgresql://localhost:5432/linketinder",
            user: "viniciusvianna",
            password: "postgres"
    ]

    static executeInstance(Function execute) {
        Sql.withInstance(params) {
            execute(it)
        }
    }
}
