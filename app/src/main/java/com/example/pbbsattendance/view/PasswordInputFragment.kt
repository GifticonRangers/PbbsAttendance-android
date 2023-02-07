package com.example.pbbsattendance.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pbbsattendance.R
import com.example.pbbsattendance.databinding.FragmentPasswordInputBinding

class PasswordInputFragment:Fragment() {

    lateinit var navController: NavController
    private var _binding : FragmentPasswordInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = this@PasswordInputFragment.findNavController()

        binding.icBackToId.setOnClickListener {
            view.findNavController().navigate(R.id.action_passwordFragment_to_idInputFragment)
        }
    }
}