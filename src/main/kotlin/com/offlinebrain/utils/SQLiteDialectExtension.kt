package com.offlinebrain.utils

import org.ktorm.expression.FunctionExpression
import org.ktorm.schema.ColumnDeclaring
import org.ktorm.schema.VarcharSqlType

fun ColumnDeclaring<String>.toUpperCase(): FunctionExpression<String> {
    // upper(str)
    return FunctionExpression(
        functionName = "upper",
        arguments = listOf(this.asExpression()),
        sqlType = VarcharSqlType
    )
}