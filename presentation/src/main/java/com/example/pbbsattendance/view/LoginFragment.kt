package com.example.pbbsattendance.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pbbsattendance.databinding.FragmentLoginBinding

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
            val idTile = "학번을 입력해주세요"
            val action = LoginFragmentDirections.actionLoginFragmentToIdInputFragment(idTile)
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateToPage(actionId: Int){
        findNavController().navigate(actionId)
    }
}