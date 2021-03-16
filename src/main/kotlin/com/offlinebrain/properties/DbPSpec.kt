package com.offlinebrain.properties

import com.uchuhimo.konf.ConfigSpec


object DBProperties : ConfigSpec("db") {
    val url by required<String>()
}