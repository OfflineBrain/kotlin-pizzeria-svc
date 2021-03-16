package com.offlinebrain.service

import com.offlinebrain.model.orm.Category
import com.offlinebrain.model.orm.MenuItem
import com.offlinebrain.repository.CategoryRepository
import com.offlinebrain.repository.MenuRepository

class MenuService(
    private val menuRepository: MenuRepository,
    private val categoryRepository: CategoryRepository
) {
    fun createItem(menuItem: MenuItem) =
        menuRepository.save(menuItem)


    fun findItem(id: Int) =
        menuRepository.findOne(id)


    fun findItemsByNamePart(name: String) =
        menuRepository.findByNamePart(name)

    fun getItemsInCategory(category: Category): List<MenuItem> {
        val categoryId = when (category.id) {
            null -> categoryRepository.findByName(category.name)?.id
            else -> category.id
        }

        return when (categoryId) {
            null -> emptyList()
            else -> menuRepository.findByCategory(categoryId)
        }
    }

    fun removeItem(id: Int) =
        menuRepository.delete(id)
}