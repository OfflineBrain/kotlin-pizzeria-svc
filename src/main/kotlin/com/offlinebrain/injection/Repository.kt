package com.offlinebrain.injection

import com.offlinebrain.repository.CategoryRepository
import com.offlinebrain.repository.CustomerRepository
import com.offlinebrain.repository.MenuRepository
import com.offlinebrain.repository.OrderRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CategoryRepository(get()) }
    single { CustomerRepository(get()) }
    single { MenuRepository(get()) }
    single { OrderRepository(get()) }
}