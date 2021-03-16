package com.offlinebrain.repository

import com.offlinebrain.model.orm.Category
import com.offlinebrain.model.orm.CategoryTable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class CategoryRepository(private val database: Database) {
    private val categories get() = database.sequenceOf(CategoryTable)

    fun save(category: Category): Int {
        return database.insertAndGenerateKey(CategoryTable, {
            set(CategoryTable.name, category.name)
        }) as Int
    }

    fun update(category: Category) {
        database.update(CategoryTable, {
            set(CategoryTable.name, category.name)

            where {
                CategoryTable.id eq (category.id ?: -1)
            }
        })
    }

    fun findOne(id: Int): Category? {
        return categories
            .find { it.id eq id }
    }

    fun findByName(name: String): Category? {
        return categories
            .find { it.name eq name }
    }

    fun getAll() = categories.toList()
}