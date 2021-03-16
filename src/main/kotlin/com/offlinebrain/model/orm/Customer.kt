package com.offlinebrain.model.orm

import org.ktorm.entity.Entity

interface Customer : Entity<Customer> {
    companion object : Entity.Factory<Customer>()

    var id: Int?
    var name: String
    var phone: String
    var address: String?
    var telegram: String
}
