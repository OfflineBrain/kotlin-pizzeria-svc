package com.offlinebrain.model.orm

import org.ktorm.schema.Table
import org.ktorm.schema.decimal
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object MenuItemTable : Table<MenuItem>("menu") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val description = varchar("description").bindTo { it.description }
    val price = decimal("price").bindTo { it.price }
    val weight = int("weight").bindTo { it.weight }
    val category = int("category").references(CategoryTable) { it.category }
}