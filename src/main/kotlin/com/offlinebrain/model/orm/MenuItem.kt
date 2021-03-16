package com.offlinebrain.model.orm

import org.ktorm.entity.Entity
import java.math.BigDecimal

interface MenuItem : Entity<MenuItem> {
    companion object : Entity.Factory<MenuItem>()

    var id: Int?
    var name: String
    var description: String
    var price: BigDecimal
    var weight: Int
    var category: Category
}