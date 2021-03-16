package com.offlinebrain.model.orm

import org.ktorm.entity.Entity
import java.time.LocalDateTime

interface Order : Entity<Order> {
    companion object : Entity.Factory<Order>()

    var id: Int?
    var items: List<Pair<Int, Int>>
    var customer: Customer
    var comment: String
    var delivery: Boolean
    var addres: String?
    var phone: String?
    var status: Status
    var created: LocalDateTime
    var updated: LocalDateTime?
}