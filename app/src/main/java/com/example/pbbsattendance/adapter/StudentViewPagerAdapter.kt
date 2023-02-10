package com.example.pbbsattendance.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pbbsattendance.view.AttendanceCheckFragment
import com.example.pbbsattendance.view.AttendanceHistoryFragment
import com.example.pbbsattendance.view.AttendanceStatusFragment

const val ATTENDANCE_CHECK_PAGE_INDEX = 0
const val ATTENDANCE_STATUS_PAGE_INDEX = 1
const val ATTENDANCE_HISTORY_PAGE_INDEX = 2

class StudentViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        ATTENDANCE_CHECK_PAGE_INDEX to { AttendanceCheckFragment() },
        ATTENDANCE_STATUS_PAGE_INDEX to { AttendanceStatusFragment() },
        ATTENDANCE_HISTORY_PAGE_INDEX to { AttendanceHistoryFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}