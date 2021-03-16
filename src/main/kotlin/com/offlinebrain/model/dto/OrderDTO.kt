package com.offlinebrain.model.dto

import com.offlinebrain.model.orm.Status
import java.time.LocalDateTime

interface OrderDTO {

    var id: Int?
    var items: List<Pair<Int, Int>>
    var customerId: Int
    var comment: String
    var delivery: Boolean
    var addres: String?
    var phone: String?
    var status: Status
    var created: LocalDateTime
    var updated: LocalDateTime?
}

class InputOrderDTO(
    override var id: Int?,
    override var items: List<Pair<Int, Int>>,
    override var customerId: Int,
    override var comment: String,
    override var delivery: Boolean,
    override var addres: String?,
    override var phone: String?,
    override var status: Status,
    override var created: LocalDateTime,
    override var updated: LocalDateTime?
) : OrderDTO

class OutputOrderDTO(
    override var id: Int?,
    override var items: List<Pair<Int, Int>>,
    override var customerId: Int,
    override var comment: String,
    override var delivery: Boolean,
    override var addres: String?,
    override var phone: String?,
    override var status: Status,
    override var created: LocalDateTime,
    override var updated: LocalDateTime?
) : OrderDTO