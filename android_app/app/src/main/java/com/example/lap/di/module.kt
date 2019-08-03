package com.example.lap.di

import android.util.Log
import com.example.lap.Lapplication
import com.example.lap.viewmodel.LoginViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { Lapplication() }
}

val networkModule = module {
    factory {
        Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
//                .addQueryParameter("token", get<AndroidPreferences>(ModuleName.UserPreferences).getString(Constants.PREFS_KEY_ANDROID_TOKEN, ""))
                .build()

            val request = original.newBuilder()
//                .header("token", get<AndroidPreferences>(ModuleName.UserPreferences).getString(Constants.PREFS_KEY_ANDROID_TOKEN, ""))
                .url(url)
                .build()

            chain.proceed(request)
        }
    }

    factory {
        var client = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor { message ->
            val maxLogSize = 1000
            for (i in 0..message.length / maxLogSize) {
                val start = i * maxLogSize
                var end = (i + 1) * maxLogSize
                end = if (end > message.length) message.length else end
                Log.v("Network Intercept", message.substring(start, end))
            }
        }
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(get())
        client.addInterceptor(logging)
        client.build()
    }

    factory(named("base")) {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(get<String>(named("api_base_url")))
            .client(get())
            .build()
    }

    factory(named("app")) {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(get<String>(named("app_base_url")))
            .build()
    }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
}

object ModuleName {
    val ResourceContext = StringQualifier("RESOURCE_CONTEXT")
}