package com.example.hackathon2021.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hackathon2021.R
import com.example.hackathon2021.databinding.SignUpResFragmentBinding

class SignUpResFragment : Fragment() {
    private lateinit var binding: SignUpResFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpResFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBackSignUpResFragment.setOnClickListener {
            findNavController().navigate(R.id.action_signUpResFragment_to_loginFragment)
        }
    }

}