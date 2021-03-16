package com.offlinebrain.utils

import com.offlinebrain.model.orm.MenuItem
import com.offlinebrain.model.orm.OrderTable
import io.kotest.core.spec.style.AnnotationSpec
import org.junit.jupiter.api.Assertions.assertNotNull

class JacksonMapperTest : AnnotationSpec() {
    @Test
    fun testMenuItemsSerialization() {
        val items = listOf(
            MenuItem {
                id = 1
            } to 1,
            MenuItem {
                id = 3
            } to 8
        )

        val message = JacksonMapper.mapper.writeValueAsString(items)
        print(message)
        assertNotNull(message)

        val readValue = JacksonMapper.mapper.readValue(message, OrderTable.stringToItemsMap)
        readValue.forEach {
            print(it.first.id)
        }
    }
}