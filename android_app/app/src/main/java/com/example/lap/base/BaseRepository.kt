package com.example.lap.base

import android.content.Context
import android.net.ConnectivityManager
import com.example.lap.Lapplication
import com.example.lap.model.ServerResponse
import org.koin.core.KoinComponent
import retrofit2.HttpException
import retrofit2.Response

open class BaseRepository : KoinComponent {


    val isNetworkConnected: Boolean
        get() {
            val cm =
                Lapplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }

    suspend fun <T : Any> safeNetworkCalls(call: suspend () -> Response<ServerResponse<T>>): Result {
        try {
            if (!isNetworkConnected) {
                throw Exception("Please check internet connection.")
            }
            val apiResult = call.invoke()
            if (apiResult.isSuccessful) {
                apiResult.body()?.let { return Result.Success(it) }
                    ?: throw IllegalStateException("response body is empty")
            } else {
                throw HttpException(apiResult)
            }
        } catch (e: Exception) {
            return Result.Error(ResponseInterceptor.handleHttpError(e))
        }
    }

    suspend fun <T> safeDatabaseCalls(call: suspend () -> T): Result {
        return try {
            val apiResult = call.invoke()
            Result.Success(apiResult)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}