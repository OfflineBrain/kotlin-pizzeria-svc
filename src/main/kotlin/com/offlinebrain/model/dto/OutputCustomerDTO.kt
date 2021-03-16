package com.offlinebrain.model.dto

interface CustomerDTO {
    var id: Int?
    var name: String
    var phone: String
    var telegram: String
    var address: String?
}

data class OutputCustomerDTO(
    override var id: Int?,
    override var name: String,
    override var phone: String,
    override var telegram: String,
    override var address: String?
) : CustomerDTO

data class InputCustomerDTO(
    override var id: Int?,
    override var name: String,
    override var phone: String,
    override var telegram: String,
    override var address: String?
) : CustomerDTO