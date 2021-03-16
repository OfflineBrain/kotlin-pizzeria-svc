package com.offlinebrain.injection

import com.offlinebrain.route.CategoryRoute
import com.offlinebrain.route.CustomerRoute
import com.offlinebrain.route.MenuRoute
import com.offlinebrain.route.OrderRoute
import org.koin.dsl.module

val routeModule = module {
    single { CategoryRoute(get(), get()) }
    single { CustomerRoute(get()) }
    single { MenuRoute(get()) }
    single { OrderRoute(get(), get()) }
}