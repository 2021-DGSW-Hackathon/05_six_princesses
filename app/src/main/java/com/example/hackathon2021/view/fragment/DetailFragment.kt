package com.example.hackathon2021.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon2021.R
import com.example.hackathon2021.adapter.CommentListAdapter
import com.example.hackathon2021.data.Board
import com.example.hackathon2021.data.req.ReqPostComment
import com.example.hackathon2021.databinding.DetailFragmentBinding
import com.example.hackathon2021.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    lateinit var binding: DetailFragmentBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var commentListAdapter: CommentListAdapter
    private lateinit var thisBoard: Board

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(binding.tvTitleDetailFragment, "title_${args.board.title}")
        ViewCompat.setTransitionName(binding.textView4, "duration_${args.board.content}")

        commentListAdapter = CommentListAdapter { (deleteComment(it)) }
        thisBoard = args.board
        binding.board = thisBoard
        binding.rvCommentsDetailFragment.apply {
            adapter = commentListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.tvCommentsCountDetailFragment.text = args.board.commentsNum.toString()
        binding.btnPostCommentDetailFragment.setOnClickListener { postComment() }
        getComments()
        observe()
    }

    private fun observe() {
        viewModel.resGetComments.observe(viewLifecycleOwner, Observer {
            commentListAdapter.submitList(it)
        })
        viewModel.resPostComment.observe(viewLifecycleOwner, Observer {
            binding.edtCommentDetailFragment.text = null
            getComments()
            thisBoard.commentsNum++
            binding.tvCommentsCountDetailFragment.text = args.board.commentsNum.toString()
        })
        viewModel.resDeleteComment.observe(viewLifecycleOwner, Observer {
            getComments()
            thisBoard.commentsNum--
            binding.tvCommentsCountDetailFragment.text = args.board.commentsNum.toString()
        })
    }

    private fun getComments() {
        viewModel.getComments(args.board.id)
    }

    private fun postComment() {
        val comment = binding.edtCommentDetailFragment.text.toString()
        if (comment.isNotBlank()) {
            viewModel.postComment(ReqPostComment(comment = comment, postId = args.board.id))
        }
    }

    private fun deleteComment(commentId: Int) {
        viewModel.deleteComment(commentId)
    }


}