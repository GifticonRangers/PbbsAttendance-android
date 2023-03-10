package com.example.pbbsattendance.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.pbbsattendance.R
import com.example.pbbsattendance.adapter.StudentViewPagerAdapter
import com.example.pbbsattendance.databinding.FragmentStudentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class StudentViewPagerFragment : Fragment() {

    lateinit var navController: NavController
    private var _binding : FragmentStudentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentViewPagerBinding.inflate(inflater, container, false)
        val viewPager = binding.viewpagerStudent

        viewPager.adapter = StudentViewPagerAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icBackToHome.setOnClickListener {
            view.findNavController().navigate(R.id.action_studentViewPagerFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}