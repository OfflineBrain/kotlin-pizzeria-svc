package com.offlinebrain.model.orm

import org.ktorm.entity.Entity

interface Category : Entity<Category> {
    companion object : Entity.Factory<Category>()

    var id: Int?
    var name: String
}