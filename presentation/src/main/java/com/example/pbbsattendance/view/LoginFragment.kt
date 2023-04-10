package com.example.pbbsattendance.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pbbsattendance.R
import com.example.pbbsattendance.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

class LoginFragment : Fragment() {

    lateinit var navController: NavController
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = this@LoginFragment.findNavController()

        binding.buttonProfessor.setOnClickListener {
            val idTitle = "교번을 입력해주세요"
            val action = LoginFragmentDirections.actionLoginFragmentToIdInputFragment(idTitle)
            view.findNavController().navigate(action)
        }

        binding.buttonStudent.setOnClickListener {
            val idTitle = "학번을 입력해주세요"
            val action = LoginFragmentDirections.actionLoginFragmentToIdInputFragment(idTitle)
            view.findNavController().navigate(action)
        }

        binding.buttonSignup.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}