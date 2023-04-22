package com.example.pbbsattendance

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pbbsattendance.databinding.ActivityMainBinding
import com.example.pbbsattendance.eventbus.ScheduleSubjectEvent
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onScheduleSubject(event: ScheduleSubjectEvent){
        Log.i("MainActivity::EventBusData","${event.scheduleSubject.nameSubject.toString()}, ${event.scheduleSubject.locationSubject.toString()},${event.scheduleSubject.id.toString()}")
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
