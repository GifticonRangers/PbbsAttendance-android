package com.example.pbbsattendance.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pbbsattendance.R
import com.example.pbbsattendance.databinding.FragmentSignupBinding
import com.example.pbbsattendance.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment:Fragment() {

    lateinit var  navController: NavController
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        navController = this@SignupFragment.findNavController()


        binding.apply {
            buttonSignup.setOnClickListener {
                view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
            }
            buttonCheckId.setOnClickListener {
                val result = inputSignupId.text.toString()
                viewModel?.checkId(result)
            }
            viewModel?.checkIdResult?.observe(viewLifecycleOwner, Observer {
                when(it){
                    true -> checkIdSuccess.visibility = View.VISIBLE
                    false -> checkIdError.visibility = View.VISIBLE
                }
            })
            inputSignupId.doOnTextChanged { text, start, before, count ->
                checkIdSuccess.visibility = View.INVISIBLE
                checkIdError.visibility = View.INVISIBLE
            }
            
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}