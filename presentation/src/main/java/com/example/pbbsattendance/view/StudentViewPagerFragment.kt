package com.example.pbbsattendance.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.pbbsattendance.R
import com.example.pbbsattendance.adapter.ATTENDANCE_CHECK_PAGE_INDEX
import com.example.pbbsattendance.adapter.ATTENDANCE_HISTORY_PAGE_INDEX
import com.example.pbbsattendance.adapter.ATTENDANCE_STATUS_PAGE_INDEX
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
        val tabLayout = binding.tabLayout

        viewPager.adapter = StudentViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    private fun getTabTitle(position: Int): String?{
        return when(position) {
            ATTENDANCE_CHECK_PAGE_INDEX -> "출결 관리"
            ATTENDANCE_STATUS_PAGE_INDEX -> "출결 현황"
            ATTENDANCE_HISTORY_PAGE_INDEX -> "출결 기록"
            else -> null
        }
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