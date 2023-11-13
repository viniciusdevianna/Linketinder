package linketinder.data

import groovy.sql.Sql
import linketinder.data.interfaces.IDatabaseConnector
import org.apache.groovy.internal.util.Function

class PostgresqlDatabaseConnector implements IDatabaseConnector{
    private static PostgresqlDatabaseConnector instance
    private static Map params

    private PostgresqlDatabaseConnector() {}

    static PostgresqlDatabaseConnector getInstance() {
        if (instance == null) {
            params = [
                    url: "jdbc:postgresql://localhost:5432/linketinder",
                    user: "postgres",
                    password: "DevU!1807"
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
