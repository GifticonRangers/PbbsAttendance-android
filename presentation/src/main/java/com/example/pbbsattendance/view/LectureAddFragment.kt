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
import com.example.pbbsattendance.databinding.FragmentLectureAddBinding

class LectureAddFragment : Fragment() {
    lateinit var navController: NavController
    private var _binding : FragmentLectureAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLectureAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = this@LectureAddFragment.findNavController()

        binding.icCancelAddLecture.setOnClickListener {
            view.findNavController().navigate(R.id.action_lectureAddFragment_to_homeFragment)
        }

    }
}