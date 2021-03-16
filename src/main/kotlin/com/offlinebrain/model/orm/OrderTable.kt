package com.offlinebrain.model.orm

import com.fasterxml.jackson.core.type.TypeReference
import org.ktorm.jackson.json
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.datetime
import org.ktorm.schema.enum
import org.ktorm.schema.int
import org.ktorm.schema.text
import org.ktorm.schema.varchar


object OrderTable : Table<Order>("cart") {
    val stringToItemsMap = object : TypeReference<List<Pair<MenuItem, Int>>>() {}

    val id = int("id").primaryKey().bindTo { it.id }
    val items = json<List<Pair<Int, Int>>>("items").bindTo { it.items }
    val customer = int("customer").references(CustomerTable) { it.customer }
    val comment = varchar("comment").bindTo { it.comment }
    val delivery = boolean("delivery").bindTo { it.delivery }
    val address = text("address").bindTo { it.addres }
    val phone = text("phone").bindTo { it.phone }
    val status = enum<Status>("status").bindTo { it.status }
    val createdDate = datetime("createdDate").bindTo { it.created }
    val updatedDate = datetime("updatedDate").bindTo { it.updated }
}