package com.example.pbbsattendance.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.pbbsattendance.R
import com.example.pbbsattendance.adapter.*
import com.example.pbbsattendance.databinding.FragmentProfessorViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProfessorViewPagerFragment : Fragment() {

    lateinit var navConroller: NavController
    private var _binding: FragmentProfessorViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfessorViewPagerBinding.inflate(inflater, container, false)
        val viewPager = binding.viewpagerProfessor
        val tabLayout = binding.tabLayout2

        viewPager.adapter = ProfessorViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }
    private fun getTabTitle(position: Int): String?{
        return when(position) {
            ATTENDANCE_MANAGE_PAGE_INDEX -> "출결 관리"
            STUDENT_LIST_PAGE_INDEX -> "학생 명단"
            WITHHOLD_LIST_PAGE_INDEX-> "출석 보류"
            else -> null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icBackToHome2.setOnClickListener {
            view.findNavController().navigate(R.id.action_professorViewPagerFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}