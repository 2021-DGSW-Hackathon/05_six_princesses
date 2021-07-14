package com.example.hackathon2021.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon2021.R
import com.example.hackathon2021.data.Comment
import com.example.hackathon2021.util.CommentDiffUtil
import java.time.LocalDateTime

class CommentListAdapter(val onDeleteBtnClick: (commentId: Int) -> Unit) :
    ListAdapter<Comment, CommentListAdapter.ViewHolder>(CommentDiffUtil()) {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWriter: TextView = view.findViewById(R.id.tv_writer_rv_item_comments)
        val tvTime: TextView = view.findViewById(R.id.tv_time_rv_item_comments)
        val tvContent: TextView = view.findViewById(R.id.tv_comment_rv_item_comments)
        val btnDelete: ImageView = view.findViewById(R.id.btn_delete_rv_item_comment)
        fun bind(comment: Comment) {
            if (comment.isMe) {
                tvWriter.text = "나"
                tvWriter.setTextColor(Color.parseColor("#FF6200EE"))
                btnDelete.apply {
                    visibility = View.VISIBLE
                    setOnClickListener { onDeleteBtnClick.invoke(comment.id) }
                }
            } else {
                if (comment.isAdmin){
                    tvWriter.text = "${comment.user.grade}-${comment.user.classNum} ${comment.user.name}"
                }else{
                    tvWriter.text = comment.user.name
                }
                btnDelete.visibility = View.INVISIBLE
                tvWriter.setTextColor(Color.BLACK)
            }
            if (comment.isWriter) {
                tvWriter.append(" (작성자)")
                tvWriter.setTextColor(Color.parseColor("#FF6200EE"))
            }
            tvContent.text = comment.comment
            tvTime.text = comment.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_comments, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}