package com.offlinebrain.route

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.offlinebrain.model.convert.toOutputDTO
import com.offlinebrain.model.convert.toPersistenceModel
import com.offlinebrain.model.dto.OutputCategoryDTO
import com.offlinebrain.model.dto.InputCategoryDTO
import com.offlinebrain.model.dto.OutputMenuItemDTO
import com.offlinebrain.service.CategoryService
import com.offlinebrain.service.MenuService
import io.ktor.features.*

class CategoryRoute(
    private val categoryService: CategoryService,
    private val menuService: MenuService
) : Route {
    override fun register(schema: SchemaBuilder) {
        schema.apply {
            query("category") {
                resolver { id: Int?, name: String? ->
                    return@resolver when {
                        id != null -> categoryService.findCategory(id)?.toOutputDTO()
                        name != null -> categoryService.findCategory(name)?.toOutputDTO()
                        else -> throw NotFoundException()
                    }
                }
            }

            query("categories") {
                resolver { -> categoryService.getAllCategories().map { it.toOutputDTO() } }
            }

            mutation("createCategory") {

                description = "Creates new category"

                resolver { dto: InputCategoryDTO ->
                    categoryService.createCategory(dto.toPersistenceModel()).toOutputDTO()
                }
            }

            type<OutputCategoryDTO> {
                property<List<OutputMenuItemDTO>>("menuItems") {
                    description = "Menu list for given Category"
                    resolver { categoryDTO -> menuService.getItemsInCategory(categoryDTO.toPersistenceModel()).toOutputDTO() }
                }
            }
            inputType<InputCategoryDTO>()
        }
    }
}