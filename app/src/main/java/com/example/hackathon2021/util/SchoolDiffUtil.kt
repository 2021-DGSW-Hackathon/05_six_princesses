package com.example.hackathon2021.util

import androidx.recyclerview.widget.DiffUtil
import com.example.hackathon2021.data.res.ResSearchSchool

class SchoolDiffUtil : DiffUtil.ItemCallback<ResSearchSchool>() {
    override fun areItemsTheSame(oldItem: ResSearchSchool, newItem: ResSearchSchool): Boolean {
        return oldItem.code==newItem.code
    }

    override fun areContentsTheSame(oldItem: ResSearchSchool, newItem: ResSearchSchool): Boolean {
        return oldItem==newItem
    }
}