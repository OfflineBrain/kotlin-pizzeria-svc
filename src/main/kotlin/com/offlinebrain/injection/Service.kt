package com.offlinebrain.injection

import com.offlinebrain.service.CategoryService
import com.offlinebrain.service.CustomerService
import com.offlinebrain.service.MenuService
import com.offlinebrain.service.OrderService
import org.koin.dsl.module

val serviceModule = module {
    single { CategoryService(get()) }
    single { CustomerService(get()) }
    single { MenuService(get(), get()) }
    single { OrderService(get(), get()) }
}