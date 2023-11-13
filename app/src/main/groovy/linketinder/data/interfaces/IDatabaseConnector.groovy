package linketinder.data.interfaces

import org.apache.groovy.internal.util.Function

interface IDatabaseConnector {
    void executeInstance(Function execute)
}