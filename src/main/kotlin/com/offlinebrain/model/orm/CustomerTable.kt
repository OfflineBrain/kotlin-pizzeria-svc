package com.offlinebrain.model.orm

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object CustomerTable : Table<Customer>("customer") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("firstname").bindTo { it.name }
    val phone = varchar("phone").bindTo { it.phone }
    val address = varchar("address").bindTo { it.address }
    val telegram = varchar("telegramChatId").bindTo { it.telegram }
}