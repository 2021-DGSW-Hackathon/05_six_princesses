package com.example.hackathon2021.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Board(
    var commentsNum: Int,
    val content: String,
    val date: String,
    val id: Int,
    val isMe: Boolean,
    val isUpdated: Boolean,
    val title: String,
    val user: User
) : Parcelable
