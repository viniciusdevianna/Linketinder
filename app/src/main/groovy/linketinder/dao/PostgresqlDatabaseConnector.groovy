package linketinder.dao

import groovy.sql.Sql
import linketinder.dao.interfaces.IDatabaseConnector
import org.apache.groovy.internal.util.Function

class PostgresqlDatabaseConnector implements IDatabaseConnector{
    private static PostgresqlDatabaseConnector instance
    private static Map params

    private PostgresqlDatabaseConnector() {}

    static PostgresqlDatabaseConnector getInstance() {
        if (instance == null) {
            params = [
                    url: "jdbc:postgresql://localhost:5432/linketinder",
                    user: "viniciusvianna",
                    password: "postgres"
            ]
            instance = new PostgresqlDatabaseConnector()
        }

        return instance
    }

    void executeInstance(Function execute) {
        Sql.withInstance(params) {
            try {
                execute(it)
            } catch (Exception e) {
                println e
            }
        }

    }
}
