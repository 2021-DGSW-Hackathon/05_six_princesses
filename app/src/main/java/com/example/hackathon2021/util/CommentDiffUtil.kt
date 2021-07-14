package com.example.hackathon2021.util

import androidx.recyclerview.widget.DiffUtil
import com.example.hackathon2021.data.Comment

class CommentDiffUtil : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem==newItem
    }
}