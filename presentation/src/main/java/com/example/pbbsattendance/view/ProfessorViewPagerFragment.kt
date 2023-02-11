package com.example.pbbsattendance.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pbbsattendance.viewmodel.ProfessorViewPagerViewModel
import com.example.pbbsattendance.R

class ProfessorViewPagerFragment : Fragment() {

    companion object {
        fun newInstance() = ProfessorViewPagerFragment()
    }

    private lateinit var viewModel: ProfessorViewPagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_professor_view_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfessorViewPagerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}