package com.example.pbbsattendance.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.dto.LoginDto
import com.example.pbbsattendance.R
import com.example.pbbsattendance.databinding.FragmentPasswordInputBinding
import com.example.pbbsattendance.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordInputFragment:Fragment() {

    lateinit var navController: NavController
    private var _binding : FragmentPasswordInputBinding? = null
    private val binding get() = _binding!!
    private val argFromInput: PasswordInputFragmentArgs by navArgs()
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_password_input, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = this@PasswordInputFragment.findNavController()
        binding.viewModel = loginViewModel

        binding.apply {
            icBackToId.setOnClickListener {
                view.findNavController().navigate(R.id.action_passwordInputFragment_to_idInputFragment)
            }
            buttonLogin.setOnClickListener {
                val dto = LoginDto(
                    idUser = argFromInput.idValue.toString(),
                    pwUser = inputPassword.text.toString()
                )
                viewModel?.login(dto)
            }
            viewModel?.loginResult?.observe(viewLifecycleOwner, Observer {
                view.findNavController().navigate(R.id.action_passwordInputFragment_to_homeFragment)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}