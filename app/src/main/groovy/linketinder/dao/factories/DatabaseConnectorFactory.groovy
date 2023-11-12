package linketinder.dao.factories

import linketinder.dao.PostgresqlDatabaseConnector
import linketinder.dao.enums.DatabaseTypes
import linketinder.dao.interfaces.IDatabaseConnector

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
