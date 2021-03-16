package com.offlinebrain.model.dto

interface MenuItemDTO<T> {
    var id: Int?
    var name: String
    var description: String
    var price: String
    var weight: Int
    var category: T
}

data class OutputMenuItemDTO(
    override var id: Int?,
    override var name: String,
    override var description: String,
    override var price: String,
    override var weight: Int,
    override var category: OutputCategoryDTO
) : MenuItemDTO<OutputCategoryDTO>

data class InputMenuItemDTO(
    override var id: Int?,
    override var name: String,
    override var description: String,
    override var price: String,
    override var weight: Int,
    override var category: Int
) : MenuItemDTO<Int>