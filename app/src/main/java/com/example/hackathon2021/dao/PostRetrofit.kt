package com.example.hackathon2021.dao

import com.example.hackathon2021.data.Board
import com.example.hackathon2021.data.Comment
import com.example.hackathon2021.data.req.ReqPost
import com.example.hackathon2021.data.req.ReqPostComment
import com.example.hackathon2021.data.res.Res
import retrofit2.Response
import retrofit2.http.*

interface PostRetrofit {
    @POST("post")
    suspend fun post(
        @Body
        reqPost: ReqPost
    ): Response<Res<Any>>

    @GET("post")
    suspend fun get(): Response<Res<List<Board>>>

    @GET("comment/{postId}")
    suspend fun getComments(@Path("postId") postId: Int): Response<Res<List<Comment>>>

    @POST("comment")
    suspend fun postComment(
        @Body
        reqPostComment: ReqPostComment
    ): Response<Res<Any>>

    @GET("post/search/{searchValue}")
    suspend fun searchPost(
        @Path("searchValue") value: String
    ): Response<Res<List<Board>>>

    @DELETE("post/{postId}")
    suspend fun deleteBoard(
        @Path("postId") postId: Int
    ): Response<Res<Any>>

    @DELETE("comment/{commentId}")
    suspend fun deleteComment(
        @Path("commentId") commentId: Int
    ): Response<Res<Any>>
}