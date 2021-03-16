package com.offlinebrain.route

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder

interface Route {
    fun register(schema: SchemaBuilder)
}