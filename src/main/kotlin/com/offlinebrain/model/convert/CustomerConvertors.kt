package com.offlinebrain.model.convert

import com.offlinebrain.model.dto.CustomerDTO
import com.offlinebrain.model.dto.OutputCustomerDTO
import com.offlinebrain.model.orm.Customer

fun Customer.toOutputDTO() = OutputCustomerDTO(id, name, phone, telegram, address)

fun CustomerDTO.toPersistenceModel() = Customer {
    this@toPersistenceModel.id?.also { id = it }
    this@toPersistenceModel.address?.also { address = it }

    name = this@toPersistenceModel.name
    phone = this@toPersistenceModel.phone
    telegram = this@toPersistenceModel.telegram
}