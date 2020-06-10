package com.mooc.constellation.http

fun <T> BaseResp<T>.dataConvert(): T {
    if (error_code == 0) {
        return result
    } else {
        throw Exception(reason)
    }
}

data class BaseResp<T>(
    var error_code: Int = 0,
    var reason: String = "",
    var result: T
)