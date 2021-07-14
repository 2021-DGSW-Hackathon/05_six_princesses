package com.example.hackathon2021.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon2021.R
import com.example.hackathon2021.data.Board
import com.example.hackathon2021.databinding.RvItemBoardBinding
import com.example.hackathon2021.util.BoardDiffUtil


class BoardListAdapter(
    val onClick: (board: Board) -> Unit,
    val onDeleteBtnClick: (boardId: Int) -> Unit
) :
    ListAdapter<Board, BoardListAdapter.ViewHolder>(BoardDiffUtil()) {
    lateinit var binding: RvItemBoardBinding

    inner class ViewHolder(binding: RvItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle: TextView = binding.tvTitleRvItemBoard
        val tvContent: TextView = binding.tvContentsRvItemBoard
        val btnComments: Button = binding.btnCommentsRvItemBoard
        val tvName: TextView = binding.tvNameRvItemBoard
        val tvTime: TextView = binding.tvTimeRvItemBoard
        val btnDelete: ImageView = binding.btnDeleteRvItemBoard


        fun bind(board: Board) {
            binding.cardView.setOnClickListener {
                onClick.invoke(board)
            }
            tvTitle.text = board.title
            tvContent.text = board.content
            btnComments.apply {
                text = board.commentsNum.toString()
                setOnClickListener {
                    onClick.invoke(board)
                }
            }
            if (board.isMe) {
                tvName.apply {
                    text = "나"
                    setTextColor(Color.parseColor("#FF6200EE"))
                }
                btnDelete.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        onDeleteBtnClick.invoke(board.id)
                    }
                }
            } else {
                btnDelete.visibility = View.INVISIBLE
                tvName.apply {
                    if (board.user.name == "익명") {
                        text = board.user.name
                    } else {
                        text = "${board.user.grade}-${board.user.classNum} ${board.user.name}"
                    }
                    setTextColor(Color.BLACK)
                }
            }
            tvTime.text = board.date

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = RvItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
