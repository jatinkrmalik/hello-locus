package com.example.lap.base

sealed class Result {
    class Success<T>(val response: T) : Result()
    class Error(val exception: Exception) : Result()
}
