package com.example.hackathon2021.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val classNum: Int,
    val grade: Int,
    val id: Int,
    val name: String
) : Parcelable
