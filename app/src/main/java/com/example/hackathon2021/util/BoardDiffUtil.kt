package com.example.hackathon2021.util

import androidx.recyclerview.widget.DiffUtil
import com.example.hackathon2021.data.Board

class BoardDiffUtil : DiffUtil.ItemCallback<Board>() {
    override fun areItemsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem==newItem
    }
}