package com.offlinebrain.model.convert

import com.offlinebrain.model.dto.OrderDTO
import com.offlinebrain.model.orm.Customer
import com.offlinebrain.model.orm.Order

fun OrderDTO.toPersistenceModel() = Order {
    id = this@toPersistenceModel.id
    addres = this@toPersistenceModel.addres
    comment = this@toPersistenceModel.comment
    created = this@toPersistenceModel.created
    updated = this@toPersistenceModel.updated
    customer = Customer { id = this@toPersistenceModel.customerId }
    delivery = this@toPersistenceModel.delivery
    status = this@toPersistenceModel.status
    phone = this@toPersistenceModel.phone
    items = this@toPersistenceModel.items
}