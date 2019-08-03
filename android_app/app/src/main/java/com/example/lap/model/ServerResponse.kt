package com.example.lap.model

class ServerResponse<T> {
    var message: String? = null
    var count: Int? = null
    var result: List<T>? = null
}