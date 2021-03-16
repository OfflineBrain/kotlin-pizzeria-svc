package com.offlinebrain.service

import com.offlinebrain.model.orm.Customer
import com.offlinebrain.model.orm.MenuItem
import com.offlinebrain.model.orm.Order
import com.offlinebrain.model.orm.Status
import com.offlinebrain.repository.MenuRepository
import com.offlinebrain.repository.OrderRepository
import java.math.BigDecimal

class OrderService(
    private val orderRepository: OrderRepository,
    private val menuRepository: MenuRepository
) {
    fun createOrder(order: Order) {
        orderRepository.save(order)
    }

    fun acceptOrder(id: Int) {
        orderRepository.updateStatus(id, Status.ACCEPTED)
    }

    fun declineOrder(id: Int) {
        orderRepository.updateStatus(id, Status.DECLINED)

    }

    fun cancelOder(id: Int) {
        orderRepository.updateStatus(id, Status.CANCELED)
    }


    fun completeOrder(id: Int) {
        orderRepository.updateStatus(id, Status.COMPLETE)
    }

    fun findOrder(id: Int) = orderRepository.findOne(id)

    fun findCustomerOrders(customer: Customer) = orderRepository.allByCustomer(customer)

    fun totalPrice(id: Int): BigDecimal {
        val items = findOrder(id)?.items
        return if (items != null) {
            val ids = items.map(Pair<Int, Int>::first).toList()
            val menuItems = menuRepository.findAll(ids)
            countPrice(menuItems, items.toMap())
        } else BigDecimal.ZERO
    }

    fun totalPrice(order: Order): BigDecimal {
        val allItems = findAllItems(order)
        return countPrice(allItems, order.items.toMap())
    }

    fun findAllItems(order: Order): List<MenuItem> {
        val items = order.items
        val ids = items.map(Pair<Int, Int>::first).toList()
        return menuRepository.findAll(ids)
    }

    private fun countPrice(
        menuItems: List<MenuItem>,
        items: Map<Int, Int>
    ) = menuItems
        .sumOf { item ->
            item.price.multiply(BigDecimal(items[item.id!!]!!))
        }
}
