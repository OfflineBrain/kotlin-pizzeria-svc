package com.offlinebrain.route

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.offlinebrain.model.convert.toOutputDTO
import com.offlinebrain.model.convert.toPersistenceModel
import com.offlinebrain.model.dto.InputCustomerDTO
import com.offlinebrain.model.dto.OutputCustomerDTO
import com.offlinebrain.service.CustomerService
import io.ktor.features.*

class CustomerRoute(private val customerService: CustomerService) : Route {

    override fun register(schema: SchemaBuilder) {
        schema.apply {
            query("customer") {
                resolver { id: Int?, telegram: String? ->
                    return@resolver when {
                        id != null -> customerService.findCustomer(id)?.toOutputDTO()
                        telegram != null -> customerService.findCustomerByTelegramId(telegram)?.toOutputDTO()
                        else -> throw NotFoundException()
                    }
                }
            }

            mutation("createCustomer") {

                description = "Creates or Replaces existing customer"

                resolver { dto: InputCustomerDTO ->
                    customerService.registerCustomer(dto.toPersistenceModel()).toOutputDTO()
                }
            }

            type<OutputCustomerDTO>()
            inputType<InputCustomerDTO>()
        }
    }
}