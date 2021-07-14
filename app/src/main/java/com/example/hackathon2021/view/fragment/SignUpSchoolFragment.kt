package com.example.hackathon2021.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon2021.R
import com.example.hackathon2021.adapter.SchoolListAdapter
import com.example.hackathon2021.data.School
import com.example.hackathon2021.databinding.LoginFragmentBinding
import com.example.hackathon2021.databinding.SignUpSchoolFragmentBinding
import com.example.hackathon2021.viewmodel.SignUpSchoolViewModel

class SignUpSchoolFragment : Fragment() {
    private lateinit var binding: SignUpSchoolFragmentBinding
    private lateinit var schoolLIstAdapter: SchoolListAdapter
    private val viewModel: SignUpSchoolViewModel by viewModels()
    private var schoolList = listOf<School>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpSchoolFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()

        schoolLIstAdapter = SchoolListAdapter {
            navigateSignUp(it)
        }

        binding.rvSignUpSchoolFragment.apply {
            adapter = schoolLIstAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.edtFindSchoolSignUpSchoolFragment.addTextChangedListener {
            searchSchool(it.toString())
        }

    }

    private fun observe() {
        viewModel.searchSchoolRes.observe(viewLifecycleOwner, Observer {
            schoolLIstAdapter.submitList(it)
        })
    }

    private fun searchSchool(query:String) {
        viewModel.searchSchool(query)
    }

    private fun navigateSignUp(school: School) {
        findNavController().navigate(
            SignUpSchoolFragmentDirections.actionSignUpSchoolFragmentToSignUpFragment(
                school
            )
        )
    }


}