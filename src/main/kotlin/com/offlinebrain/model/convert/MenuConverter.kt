package com.offlinebrain.model.convert

import com.offlinebrain.model.dto.CategoryDTO
import com.offlinebrain.model.dto.MenuItemDTO
import com.offlinebrain.model.dto.OutputMenuItemDTO
import com.offlinebrain.model.orm.Category
import com.offlinebrain.model.orm.MenuItem
import java.math.BigDecimal
import java.text.DecimalFormat

val df = DecimalFormat("#0.00")

fun MenuItem.toOutputDTO() =
    OutputMenuItemDTO(id, name, description, df.format(price), weight, category.toOutputDTO())

fun <T : CategoryDTO> MenuItemDTO<T>.toPersistenceModel() = MenuItem {
    this@toPersistenceModel.id?.also { id = it }

    name = this@toPersistenceModel.name
    description = this@toPersistenceModel.description
    category = this@toPersistenceModel.category.toPersistenceModel()
    price = BigDecimal(this@toPersistenceModel.price)
    weight = this@toPersistenceModel.weight
}

@JvmName("toPersistenceModelInt")
fun MenuItemDTO<Int>.toPersistenceModel() = MenuItem {
    this@toPersistenceModel.id?.also { id = it }

    name = this@toPersistenceModel.name
    description = this@toPersistenceModel.description
    category = Category { id = this@toPersistenceModel.category }
    price = BigDecimal(this@toPersistenceModel.price)
    weight = this@toPersistenceModel.weight
}

fun List<MenuItem>.toOutputDTO() = map { it.toOutputDTO() }

fun <T : CategoryDTO> List<MenuItemDTO<T>>.toPersistenceModel() = map { it.toPersistenceModel() }

@JvmName("toPersistenceModelInt")
fun List<MenuItemDTO<Int>>.toPersistenceModel() = map { it.toPersistenceModel() }