package com.example.hackathon2021.data.req

data class ReqPostComment(val comment: String, val isSecret: Boolean = true, val postId: Int)
