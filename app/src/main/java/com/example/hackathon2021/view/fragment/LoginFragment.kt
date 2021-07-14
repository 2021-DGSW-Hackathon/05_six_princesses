package com.example.hackathon2021.view.fragment

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.hackathon2021.R
import com.example.hackathon2021.data.Account
import com.example.hackathon2021.data.res.Res
import com.example.hackathon2021.databinding.LoginFragmentBinding
import com.example.hackathon2021.util.mApplication
import com.example.hackathon2021.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    lateinit var binding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()

        binding.btnSignUpLoginFragment.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpSchoolFragment)
        }
        binding.btnLoginLoginFragment.setOnClickListener {
            login()
        }
    }

    private fun observe() {
        viewModel.loginRes.observe(viewLifecycleOwner, Observer {
            when (it?.status) {
                200 -> {
                    mApplication.prefs.token = it.data.authToken
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToMainFragment(
                            it.data.school.name
                        )
                    )
                }
                else -> {
                    if (it != null) {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                    viewModel.loginRes.postValue(null)
                }
            }
        })
    }

    private fun login() {
        val id = binding.edtIdLoginFragment.text.toString()
        val pw = binding.edtPwLoginFragment.text.toString()
        if (id.isNotBlank() && pw.isNotBlank()) {
            viewModel.login(Account(id, pw))
        }
    }
}