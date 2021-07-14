package com.example.hackathon2021.dao

import com.example.hackathon2021.data.*
import com.example.hackathon2021.data.req.ReqSignUp
import com.example.hackathon2021.data.res.Res
import com.example.hackathon2021.data.res.ResLogin
import com.example.hackathon2021.data.res.ResSearchSchool
import retrofit2.Response
import retrofit2.http.*

interface AccountRetrofit {
    @POST("auth/login")
    suspend fun login(
        @Body
        account: Account
    ): Response<Res<ResLogin>>

    @GET("school/search-school")
    suspend fun searchSchool(
        @Query("query") name: String
    ): Response<Res<List<ResSearchSchool>>>

    @POST("auth/signup")
    suspend fun signUp(
        @Body
        req: ReqSignUp
    ): Response<Res<Any>>

    @GET("auth/exist")
    suspend fun idCheck(@Query("id") id: String): Response<Res<Any>>
}