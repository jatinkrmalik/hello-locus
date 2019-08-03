package com.example.lap.base

import com.example.lap.exceptions.*
import retrofit2.HttpException
import java.net.HttpRetryException

object ResponseInterceptor {
    fun handleHttpError(exception: Exception): Exception {
        exception.message?.let {
            exception.printStackTrace()
        }

        return when (exception) {
            is HttpException -> {
                val status = (exception as HttpRetryException).responseCode()
                if (status in 400..499) {
                    if (status == 401) {
                        UserNotRegisteredException("Please Log in Again")
                    } else {
                        UrlNotFoundException("Url Not Found. Status : $status")
                    }
                } else
                    ServerRuntimeException("Server runtime exception")
            }
            is ServerException -> ServerException(exception.message!!)
            is NoInternetException -> NoInternetException("Please check your internet connection.")
            else -> {
                SomethingWentWrongException("Something went wrong. Please try again")
            }
        }
    }
}