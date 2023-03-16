package com.example.pbbsattendance.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pbbsattendance.view.AttendanceManageFragment
import com.example.pbbsattendance.view.StudentListFragment
import com.example.pbbsattendance.view.WithHoldListFragment

const val ATTENDANCE_MANAGE_PAGE_INDEX = 0
const val STUDENT_LIST_PAGE_INDEX = 1
const val WITHHOLD_LIST_PAGE_INDEX = 2

class ProfessorViewPagerAdapter(fragment: Fragment) :FragmentStateAdapter(fragment){

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        ATTENDANCE_MANAGE_PAGE_INDEX to { AttendanceManageFragment() },
        STUDENT_LIST_PAGE_INDEX to { StudentListFragment() },
        WITHHOLD_LIST_PAGE_INDEX to { WithHoldListFragment() },
    )
    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}