package com.example.hackathon2021.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon2021.viewmodel.MainViewModel
import com.example.hackathon2021.R
import com.example.hackathon2021.adapter.BoardListAdapter
import com.example.hackathon2021.data.Board
import com.example.hackathon2021.databinding.MainFragmentBinding
import com.example.hackathon2021.databinding.RvItemBoardBinding

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: MainFragmentBinding
    private val args: MainFragmentArgs by navArgs()
    private lateinit var boardListAdapter: BoardListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        binding.name = args.name
        boardListAdapter = BoardListAdapter(
            onClick = {
                navigateToDetail(it)
            },
            onDeleteBtnClick = { deleteBoard(it) }
        )
        binding.fabNavigatePostMainFragment.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_postFragment)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = boardListAdapter
        }
        binding.swipeLayoutMainFragment.setOnRefreshListener { getBoards() }
        binding.edtSearchMainFragment.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                viewModel.searchBoards(it.toString())
            } else {
                getBoards()
            }
        }
        binding.fabNavigateLogoutMainFragment.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
        getBoards()

    }

    private fun observe() {
        viewModel.resGetBoards.observe(viewLifecycleOwner, Observer {
            boardListAdapter.submitList(it)
            binding.swipeLayoutMainFragment.isRefreshing = false
        })
        viewModel.resSearchBoards.observe(viewLifecycleOwner, Observer {
            boardListAdapter.submitList(it)
            binding.swipeLayoutMainFragment.isRefreshing = false
        })
        viewModel.resDeleteBoard.observe(viewLifecycleOwner, Observer {
            getBoards()
        })
    }

    private fun navigateToDetail(board: Board) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(board)
        )
    }

    private fun getBoards() {
        viewModel.getBoards()
    }

    private fun deleteBoard(boardId: Int) {
        viewModel.deleteBoard(boardId)
    }

}