package com.example.hackathon2021.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hackathon2021.R
import com.example.hackathon2021.data.req.ReqSignUp
import com.example.hackathon2021.data.School
import com.example.hackathon2021.databinding.SignUpFragmentBinding
import com.example.hackathon2021.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {
    private val args: SignUpFragmentArgs by navArgs()
    private val viewModel: SignUpViewModel by viewModels()
    lateinit var binding: SignUpFragmentBinding
    private lateinit var school: School
    private var isIdChecked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        school = args.school
        binding.school = school
        binding.edtIdSignUpFragment.addTextChangedListener { isIdChecked = false }
        binding.btnIdCheckSignUpFragment.setOnClickListener { idCheck() }
        binding.btnNextSignUpFragment.setOnClickListener { signUp() }
    }

    private fun observe() {
        viewModel.signUpRes.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                200 -> findNavController().navigate(R.id.action_signUpFragment_to_signUpResFragment)
                else -> Toast.makeText(requireContext(), "회원가입에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.idCheckRes.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                200 -> {
                    Toast.makeText(requireContext(), "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                    isIdChecked = true
                }
                202 -> {
                    Toast.makeText(requireContext(), "중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun signUp() {
        val grade = binding.edtGradeSignUpFragment.text.toString()
        val classNum = binding.edtClassSignUpFragment.text.toString()
        val name = binding.edtNameSignUpFragment.text.toString()
        val id = binding.edtIdSignUpFragment.text.toString()
        val pw = binding.edtPwSignUpFragment.text.toString()
        val pw2 = binding.edtPw2SignUpFragment.text.toString()
        if (isIdChecked) {
            if (pwCheck(pw, pw2)) {
                viewModel.signUp(ReqSignUp(grade.toInt(), classNum.toInt(), id, pw, school.name, school.code, name))
            } else Toast.makeText(requireContext(), "형식에 맞게 입력해주세요", Toast.LENGTH_SHORT).show()
//            binding.progressBar.visibility=View.VISIBLE
        }else{
            Toast.makeText(requireContext(), "아이디 중복 체크를 해주세요", Toast.LENGTH_SHORT).show()
        }

    }

    private fun idCheck() {
        val id = binding.edtIdSignUpFragment.text.toString()
        if (id.length in 6..13) viewModel.idCheck(id)
        else Toast.makeText(requireContext(), "형식에 맞게 입력해주세요", Toast.LENGTH_SHORT).show()
    }

    private fun pwCheck(pw: String, pw2: String): Boolean {
        return pw == pw2 && pw.length in 8..17
    }

}