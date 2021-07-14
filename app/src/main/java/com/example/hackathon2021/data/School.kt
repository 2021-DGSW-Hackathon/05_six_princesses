package com.example.hackathon2021.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class School(val code: String, val name: String) : Parcelable
