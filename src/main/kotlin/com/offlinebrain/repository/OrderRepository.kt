package com.offlinebrain.repository

import com.offlinebrain.model.orm.Customer
import com.offlinebrain.model.orm.Order
import com.offlinebrain.model.orm.OrderTable
import com.offlinebrain.model.orm.Status
import org.ktorm.database.Database
import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import java.time.LocalDateTime.now

class OrderRepository(private val database: Database) {
    private val orders get() = database.sequenceOf(OrderTable)

    fun save(order: Order): Int {
        return database.insertAndGenerateKey(OrderTable, {
            setFields(order)
            set(OrderTable.createdDate, now())
        }) as Int
    }

    fun update(order: Order) {
        val id = order.id
        if (id != null) {
            database.update(OrderTable, {
                setFields(order)
                set(OrderTable.updatedDate, now())
                where {
                    OrderTable.id eq id
                }
            })
        }
    }

    fun updateStatus(id: Int, status: Status) {
        database.update(OrderTable, {
            set(OrderTable.status, status)
            set(OrderTable.updatedDate, now())

            where {
                OrderTable.id eq id
            }
        })
    }

    fun findOne(id: Int): Order? {
        return orders
            .find { it.id eq id }
    }

    fun allByCustomer(customer: Customer): List<Order> {
        return orders
            .filter { it.customer eq (customer.id ?: -1) }
            .toList()
    }

    private fun AssignmentsBuilder.setFields(order: Order) {
        set(OrderTable.customer, order.customer.id)
        set(OrderTable.comment, order.comment)
        set(OrderTable.items, order.items)
        set(OrderTable.delivery, order.delivery)
        set(OrderTable.address, order.addres)
        set(OrderTable.phone, order.phone)
    }
}