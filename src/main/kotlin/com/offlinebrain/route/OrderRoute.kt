package com.offlinebrain.route

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.offlinebrain.model.convert.df
import com.offlinebrain.model.convert.toOutputDTO
import com.offlinebrain.model.convert.toPersistenceModel
import com.offlinebrain.model.dto.InputOrderDTO
import com.offlinebrain.model.dto.OutputCustomerDTO
import com.offlinebrain.model.dto.OutputMenuItemDTO
import com.offlinebrain.model.dto.OutputOrderDTO
import com.offlinebrain.model.orm.Status
import com.offlinebrain.service.CustomerService
import com.offlinebrain.service.OrderService

class OrderRoute(
    private val orderService: OrderService,
    private val customerService: CustomerService
) : Route {
    override fun register(schema: SchemaBuilder) {
        schema.apply {

            enum<Status>()
            inputType<InputOrderDTO>()
            type<OutputOrderDTO> {
                property<String>("totalPrice") {
                    resolver {
                        df.format(orderService.totalPrice(it.toPersistenceModel()))
                    }
                }

                property<Map<OutputMenuItemDTO, Int>>("menuItems") {
                    resolver { order ->
                        val idToCount = order.items.toMap()
                        orderService.findAllItems(order.toPersistenceModel()).toOutputDTO()
                            .map { it to idToCount[it.id]!! }
                            .toMap()
                    }
                }

                property<OutputCustomerDTO?>("customer") {
                    resolver {
                        customerService.findCustomer(it.customerId)?.toOutputDTO()
                    }
                }
            }
        }
    }

}