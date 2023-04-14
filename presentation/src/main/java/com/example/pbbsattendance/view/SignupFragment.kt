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
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.domain.model.dto.UserDto
import com.example.pbbsattendance.R
import com.example.pbbsattendance.databinding.FragmentSignupBinding
import com.example.pbbsattendance.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment:Fragment() {

    lateinit var  navController: NavController
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val sinupViewModel by viewModels<SignupViewModel>()

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
        binding.viewModel = sinupViewModel
        navController = this@SignupFragment.findNavController()


        binding.apply {
            buttonSignup.setOnClickListener {
                val genderType = checkGenderType()
                val userType = checkUserType()
                val dto = UserDto(
                    id = null,
                    idUser = inputSignupId.text.toString(),
                    pwUser = inputPwConfirm.text.toString(),
                    name = inputName.text.toString(),
                    phone = inputPhoneNumber.text.toString(),
                    email= inputEmail.text.toString(),
                    department = inputDepartment.text.toString(),
                    type = genderType,
                    gender = userType
                )
                viewModel?.signup(dto)
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
            viewModel?.signupResult?.observe(viewLifecycleOwner, Observer {
                view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
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

    fun checkGenderType():Int{
        var result = 0
        if (binding.buttonFemale.isChecked) result = 1
        if (binding.buttonMale.isChecked) result = 0

        return result
    }

    fun checkUserType():Int{
        var result = 0
        if (binding.buttonProfessor.isChecked) result = 1
        if (binding.buttonStudent.isChecked) result = 2

        return result
    }
}