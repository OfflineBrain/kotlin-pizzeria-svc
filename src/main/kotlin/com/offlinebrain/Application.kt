package com.offlinebrain

import com.apurebase.kgraphql.GraphQL
import com.fasterxml.jackson.databind.SerializationFeature
import com.offlinebrain.injection.dataSourceModule
import com.offlinebrain.injection.repositoryModule
import com.offlinebrain.injection.routeModule
import com.offlinebrain.injection.serviceModule
import com.offlinebrain.route.CategoryRoute
import com.offlinebrain.route.CustomerRoute
import com.offlinebrain.route.MenuRoute
import com.offlinebrain.route.OrderRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import org.koin.core.logger.Level
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
import org.koin.logger.SLF4JLogger

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Koin) {
        SLF4JLogger(Level.DEBUG)
        modules(
            dataSourceModule,
            repositoryModule,
            serviceModule,
            routeModule
        )
    }


    install(GraphQL) {
        playground = true

        schema {
            get<CustomerRoute>().register(this)
            get<CategoryRoute>().register(this)
            get<MenuRoute>().register(this)
            get<OrderRoute>().register(this)
        }
    }
}
