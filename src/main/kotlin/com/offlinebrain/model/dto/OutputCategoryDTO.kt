package com.offlinebrain.model.dto

interface CategoryDTO {
    var id: Int?
    var name: String
}

data class OutputCategoryDTO(
    override var id: Int?,
    override var name: String
) : CategoryDTO

data class InputCategoryDTO(
    override var id: Int?,
    override var name: String
) : CategoryDTO
