package com.offlinebrain.service

import com.offlinebrain.model.orm.Customer
import com.offlinebrain.repository.CustomerRepository

class CustomerService(
    private val customerRepository: CustomerRepository
) {

    fun registerCustomer(customer: Customer): Customer {
        val existing = customerRepository.findByTelegram(customer.telegram)

        return if (existing != null) {
            val entity = Customer {
                id = existing.id
                name = customer.name
                phone = customer.phone
                customer.address.also { address = it }
            }
            customerRepository.update(entity)
        } else {
            customer.id = customerRepository.save(customer)
            customer
        }
    }

    fun findCustomer(id: Int): Customer? = customerRepository.findOne(id)

    fun findCustomerByTelegramId(id: String) = customerRepository.findByTelegram(id)
}