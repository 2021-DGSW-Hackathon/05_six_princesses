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
    private const val BASE_URL = "https://dgswhackathon2021.herokuapp.com/"
    private fun retrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(
                provideOkHttpClient(Interceptor())
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun provideOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor)
            readTimeout(100,TimeUnit.SECONDS)
            writeTimeout(100,TimeUnit.SECONDS)
            build()
        }

    val accountRetrofit: AccountRetrofit by lazy {
        retrofit().create(AccountRetrofit::class.java)
    }
    val postRetrofit: PostRetrofit by lazy {
        retrofit().create(PostRetrofit::class.java)
    }


    class Interceptor : okhttp3.Interceptor {
        override fun intercept(chain: okhttp3.Interceptor.Chain): Response = with(chain) {
            val req =
                chain.request().newBuilder().addHeader(
                    "Authorization",
                    "Bearer " + mApplication.prefs.token
                ).build()
            chain.proceed(req)
        }

    }
}