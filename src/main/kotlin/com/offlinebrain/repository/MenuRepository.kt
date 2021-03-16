package com.offlinebrain.repository

import com.offlinebrain.model.orm.MenuItem
import com.offlinebrain.model.orm.MenuItemTable
import com.offlinebrain.utils.toUpperCase
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.inList
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.like
import org.ktorm.dsl.update
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class MenuRepository(private val database: Database) {
    private val menu get() = database.sequenceOf(MenuItemTable)

    fun save(item: MenuItem): Int {
        return database.insertAndGenerateKey(MenuItemTable, {
            set(MenuItemTable.name, item.name)
            set(MenuItemTable.description, item.description)
            set(MenuItemTable.category, item.category.id)
            set(MenuItemTable.price, item.price)
            set(MenuItemTable.weight, item.weight)
        }) as Int
    }

    fun update(item: MenuItem) {
        database.update(MenuItemTable, {
            set(MenuItemTable.name, item.name)
            set(MenuItemTable.description, item.description)
            set(MenuItemTable.category, item.category.id)
            set(MenuItemTable.price, item.price)
            set(MenuItemTable.weight, item.weight)

            where {
                MenuItemTable.id eq (item.id ?: -1)
            }
        })
    }

    fun findOne(id: Int): MenuItem? {
        return menu
            .find { it.id eq id }
    }

    fun findAll(ids: List<Int>): List<MenuItem> {
        return menu
            .filter { it.id inList ids }
            .toList()
    }

    fun findByNamePart(name: String): List<MenuItem> {
        return menu
            .filter { it.name.toUpperCase() like "%${name.toUpperCase()}%" }
            .toList()
    }

    fun findByCategory(id: Int): List<MenuItem> {
        return menu
            .filter { it.category eq id }
            .toList()
    }

    fun delete(id: Int): Int {
        return database.delete(MenuItemTable, {
            it.id eq id
        })
    }
}