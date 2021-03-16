package com.offlinebrain.route

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.offlinebrain.model.convert.toOutputDTO
import com.offlinebrain.model.convert.toPersistenceModel
import com.offlinebrain.model.dto.InputMenuItemDTO
import com.offlinebrain.model.dto.OutputMenuItemDTO
import com.offlinebrain.model.orm.Category
import com.offlinebrain.service.MenuService

class MenuRoute(private val menuService: MenuService) : Route {
    override fun register(schema: SchemaBuilder) {
        schema.apply {

            query("menuCategory") {
                resolver { category: String ->
                    menuService.getItemsInCategory(Category { name = category }).toOutputDTO()
                }
            }

            query("findPosition") {
                resolver { name: String ->
                    menuService.findItemsByNamePart(name).map { it.toOutputDTO() }
                }
            }

            mutation("createPosition") {
                resolver { menuItem: InputMenuItemDTO ->
                    menuService.createItem(menuItem.toPersistenceModel())
                }
            }

            mutation("deletePosition") {
                resolver { id: Int ->
                    menuService.removeItem(id)
                }
            }


            type<OutputMenuItemDTO>()
            inputType<InputMenuItemDTO>()
        }
    }
}