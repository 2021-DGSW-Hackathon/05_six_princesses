package com.example.hackathon2021.util

import com.example.hackathon2021.dao.AccountRetrofit
import com.example.hackathon2021.dao.PostRetrofit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {
    private const val BASE_URL = "http://192.168.137.143:8080/"
    private fun retrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(Interceptor()).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val accountRetrofit: AccountRetrofit by lazy {
        retrofit().create(AccountRetrofit::class.java)
    }
    val postRetrofit: PostRetrofit by lazy {
        retrofit().create(PostRetrofit::class.java)
    }


    class Interceptor : okhttp3.Interceptor {
        override fun intercept(chain: okhttp3.Interceptor.Chain): Response {
            val req =
                chain.request().newBuilder().addHeader(
                    "Authorization",
                    "Bearer " + mApplication.prefs.token
                ).build()

            return chain.proceed(req)
        }

    }
}