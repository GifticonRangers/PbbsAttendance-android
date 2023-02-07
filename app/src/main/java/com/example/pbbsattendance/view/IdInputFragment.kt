package com.example.pbbsattendance.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pbbsattendance.R
import com.example.pbbsattendance.databinding.FragmentIdInputBinding
import com.example.pbbsattendance.viewmodel.IdInputViewModel

class IdInputFragment : Fragment() {

    lateinit var navController: NavController
    private var _binding : FragmentIdInputBinding? = null
    private val binding get() = _binding!!
    private val argFromLogin: IdInputFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIdInputBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = this@IdInputFragment.findNavController()

        binding.apply {
            textIdTitle.text = argFromLogin.idTitle.toString()
            icBackToLogin.setOnClickListener {
                view.findNavController().navigate(R.id.action_idInputFragment_to_loginFragment)
            }
            buttonNext.setOnClickListener {
                view.findNavController().navigate(R.id.action_idInputFragment_to_passwordFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
