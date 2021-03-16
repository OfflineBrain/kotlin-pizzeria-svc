package com.offlinebrain

import com.offlinebrain.properties.DBProperties
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.source.yaml
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import java.sql.DriverManager

fun main() {
    val dbProperties = Config { addSpec(DBProperties) }
        .from.yaml.resource("DBProperties.yml")
    val dbUrl = dbProperties[DBProperties.url]
    val connection = DriverManager.getConnection(dbUrl)

    try {
        val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(connection))
        val liquibase = Liquibase("migration/liquibase.yml", ClassLoaderResourceAccessor(), database)
        liquibase.update(Contexts())
    } finally {
        if (connection != null) {
            connection.rollback()
            connection.close()
        }
    }
}