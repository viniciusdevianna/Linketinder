package linketinder.data.factories

import linketinder.data.PostgresqlDatabaseConnector
import linketinder.data.enums.DatabaseTypes
import linketinder.data.interfaces.IDatabaseConnector

import java.security.InvalidParameterException

class DatabaseConnectorFactory {
    IDatabaseConnector getConnector(DatabaseTypes databaseType) {
        switch (databaseType) {
            case DatabaseTypes.POSTGRESQL:
                return PostgresqlDatabaseConnector.getInstance()
                break
            default:
                throw new InvalidParameterException("Tipo de database não implementado")
        }
    }
}
