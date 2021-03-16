package com.offlinebrain.model.convert

import com.offlinebrain.model.dto.CategoryDTO
import com.offlinebrain.model.dto.OutputCategoryDTO
import com.offlinebrain.model.orm.Category

fun Category.toOutputDTO() = OutputCategoryDTO(id, name)

fun CategoryDTO.toPersistenceModel() = Category {
    this@toPersistenceModel.id?.also { id = it }

    name = this@toPersistenceModel.name
}