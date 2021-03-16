package com.offlinebrain.repository

import com.offlinebrain.model.orm.Customer
import com.offlinebrain.model.orm.CustomerTable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf

class CustomerRepository(private val database: Database) {
    private val customers get() = database.sequenceOf(CustomerTable)

    fun save(customer: Customer): Int {
        return database.insertAndGenerateKey(CustomerTable, {
            set(CustomerTable.name, customer.name)
            set(CustomerTable.phone, customer.phone)
            set(CustomerTable.address, customer.address)
            set(CustomerTable.telegram, customer.telegram)
        }) as Int
    }

    fun update(customer: Customer): Customer {
        database.update(CustomerTable, {
            set(CustomerTable.name, customer.name)
            set(CustomerTable.phone, customer.phone)
            set(CustomerTable.address, customer.address)

            where {
                CustomerTable.id eq (customer.id ?: -1)
            }
        })
        return customer
    }

    fun findOne(id: Int): Customer? {
        return customers
            .find { it.id eq id }
    }

    fun findByPhone(phone: String): Customer? {
        return customers
            .find { it.phone eq phone }
    }

    fun findByTelegram(telegramChatId: String): Customer? {
        return customers
            .find { it.telegram eq telegramChatId }
    }
}
