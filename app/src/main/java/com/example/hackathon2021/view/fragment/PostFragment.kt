package com.example.hackathon2021.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hackathon2021.viewmodel.PostViewModel
import com.example.hackathon2021.data.req.ReqPost
import com.example.hackathon2021.databinding.PostFragmentBinding

class PostFragment : Fragment() {
    lateinit var binding: PostFragmentBinding
    private val viewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        binding.btnPostPostFragment.setOnClickListener { post() }
    }

    private fun post() {
        val isSecret = binding.switchIsSecretPostFragment.isChecked
        val title = binding.edtTitlePostFragment.text.toString()
        val content = binding.edtContentPostFragment.text.toString()
        viewModel.post(ReqPost(title, content, isSecret))
    }

    private fun observe() {
        viewModel.postRes.observe(viewLifecycleOwner, Observer {
            if (it.status != 200) {
                Toast.makeText(requireContext(), "게시글 작성에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
            findNavController().navigateUp()
        })
    }
}