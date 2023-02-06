package com.example.pbbsattendance.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pbbsattendance.R
import com.example.pbbsattendance.viewmodel.IdInputViewModel

class IdInputFragment : Fragment() {

    companion object {
        fun newInstance() = IdInputFragment()
    }

    private lateinit var viewModel: IdInputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_id_input, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IdInputViewModel::class.java)
        // TODO: Use the ViewModel
    }

}