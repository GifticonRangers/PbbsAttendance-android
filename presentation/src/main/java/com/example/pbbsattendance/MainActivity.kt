package com.example.pbbsattendance

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.pbbsattendance.databinding.ActivityMainBinding
import com.example.pbbsattendance.eventbus.ScheduleSubjectEvent
import com.example.pbbsattendance.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<MainViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewmodel = mainViewModel
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onScheduleSubject(event: ScheduleSubjectEvent){
        binding.viewmodel?.setScheduleSubject(event.scheduleSubject)
        Log.i("MainActivity::EventBusData","${event.scheduleSubject.scheduleName}, ${event.scheduleSubject.roomInfo},${event.scheduleSubject.originId}")
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
