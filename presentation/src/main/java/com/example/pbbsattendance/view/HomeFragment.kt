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
import com.github.tlaabs.timetableview.Schedule
import com.github.tlaabs.timetableview.Time
import dagger.hilt.android.AndroidEntryPoint

class HomeFragment : Fragment() {

    lateinit var navController: NavController
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = this@HomeFragment.findNavController()
        var scheduleList = ArrayList<Schedule>()
        var schedule1 = Schedule()

        schedule1.apply {
            classTitle = "캡스톤 디자인(2)"
            classPlace = "SH505"
            professorName = "백형부"
            startTime = Time(10,0)
            endTime = Time(12,50)
            day = 2
        }
        scheduleList.add(schedule1)

        binding.icPlusSchedule.setOnClickListener {
            binding.timetable.add(scheduleList)
        }
        binding.timetable.setOnStickerSelectEventListener { idx, schedules ->
            view.findNavController().navigate(R.id.action_homeFragment_to_studentViewPagerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}