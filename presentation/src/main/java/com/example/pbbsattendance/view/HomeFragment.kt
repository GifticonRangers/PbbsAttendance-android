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
import com.example.pbbsattendance.databinding.FragmentHomeBinding
import com.example.pbbsattendance.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var navController: NavController
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = this@HomeFragment.findNavController()


        binding.icPlusSchedule.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_lectureAddFragment)
        }
        /**학생화면으로 들어가기 위한 임시 네비게이팅*/
        binding.icEditSchedule.setOnClickListener {
            //view.findNavController().navigate(R.id.action_homeFragment_to_studentViewPagerFragment)
            view.findNavController().navigate(R.id.action_homeFragment_to_professorViewPagerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}