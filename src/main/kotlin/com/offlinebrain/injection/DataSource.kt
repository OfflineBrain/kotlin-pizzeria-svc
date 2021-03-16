package com.offlinebrain.injection

import com.offlinebrain.properties.DBProperties
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.source.yaml
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.dsl.bind
import org.koin.dsl.module
import org.ktorm.database.Database
import org.ktorm.support.sqlite.SQLiteDialect
import javax.sql.DataSource

val dataSourceModule = module {
    val dbProperties by lazy {
        Config { addSpec(DBProperties) }
            .from.yaml.resource("DBProperties.yml")
    }
    val dbUrl by lazy { dbProperties[DBProperties.url] }
    val config = HikariConfig()
    val dataSource: HikariDataSource by lazy {
        config.jdbcUrl = dbUrl
        HikariDataSource(config)
    }

    val database by lazy { Database.connect(dataSource, dialect = SQLiteDialect()) }

    single { dataSource } bind DataSource::class
    single { database }
}
