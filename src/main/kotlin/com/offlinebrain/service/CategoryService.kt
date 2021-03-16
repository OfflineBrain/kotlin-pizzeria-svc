package com.offlinebrain.service

import com.offlinebrain.model.orm.Category
import com.offlinebrain.repository.CategoryRepository

class CategoryService(private val categoryRepository: CategoryRepository) {

    fun createCategory(category: Category): Category {
        val id = categoryRepository.save(category)
        category.id = id
        return category
    }

    fun findCategory(id: Int) = categoryRepository.findOne(id)

    fun findCategory(name: String) = categoryRepository.findByName(name)

    fun getAllCategories() = categoryRepository.getAll()
}