package com.example.pbbsattendance.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.data.repository.SubjectRepositoryImpl
import com.example.domain.model.dto.IdDto
import com.example.domain.usecases.GetUserUseCase
import com.example.pbbsattendance.R
import com.example.pbbsattendance.databinding.FragmentHomeBinding
import com.example.pbbsattendance.util.mapPepTalk
import com.example.pbbsattendance.util.mapUserType
import com.example.pbbsattendance.viewmodel.HomeViewModel
import com.example.pbbsattendance.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var navController: NavController
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val baseInflater = LayoutInflater.from(requireActivity())
        _binding = DataBindingUtil.inflate(baseInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = this@HomeFragment.findNavController()
        binding.viewModel = homeViewModel

        binding.apply {
            viewModel?.getUser(IdDto(id=3))

            icPlusSchedule.setOnClickListener {
                view.findNavController().navigate(R.id.action_homeFragment_to_lectureAddFragment)
            }

            icEditSchedule.setOnClickListener {
                //view.findNavController().navigate(R.id.action_homeFragment_to_studentViewPagerFragment)
                view.findNavController().navigate(R.id.action_homeFragment_to_professorViewPagerFragment)
            }
            viewModel?.user?.observe(viewLifecycleOwner, Observer {
                viewModel?.showScheduleSubjects(IdDto(it.id!!))
                userName.text = it.nameUser
                userType.text = mapUserType(it.typeUser?.koName)
                peptalk.text = mapPepTalk(it.typeUser?.koName)
            })
            viewModel?.scheduleSubjectsResult?.observe(viewLifecycleOwner, Observer {
                Log.i("scheduleSubjectsResult","timetable create")
                it.forEach {
                    Log.i("Result.classTitle","${it.classTitle}")
                    Log.i("Result.day","${it.day}")
                    Log.i("Result.startTime","${it.startTime}")
                    Log.i("Result.endTime","${it.endTime}")
                    Log.i("Result.professorName","${it.professorName}")
                    Log.i("Result.classPlace","${it.classPlace}")
                }

                timetable.add(it)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}