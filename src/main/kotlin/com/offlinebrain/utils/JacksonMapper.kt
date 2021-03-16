package com.offlinebrain.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.ktorm.jackson.KtormModule


object JacksonMapper {
    val mapper: ObjectMapper by lazy {
        val objectMapper = ObjectMapper()
        objectMapper.registerKotlinModule()
        objectMapper.registerModule(KtormModule())
    }
}