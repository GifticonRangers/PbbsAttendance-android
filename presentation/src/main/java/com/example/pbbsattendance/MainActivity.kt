package com.example.pbbsattendance

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NfcA
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.pbbsattendance.databinding.ActivityMainBinding
import com.example.pbbsattendance.eventbus.LectureTimeItemEvent
import com.example.pbbsattendance.eventbus.ScheduleSubjectEvent
import com.example.pbbsattendance.eventbus.UserEvent
import com.example.pbbsattendance.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<MainViewModel>()
    private var nfcAdapter:NfcAdapter? = null
    private lateinit var pendingIntent: PendingIntent
    private lateinit var ndef:IntentFilter
    private lateinit var filter: Array<IntentFilter>
    private lateinit var techList: Array<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewmodel = mainViewModel
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val targetIntent = Intent(this,MainActivity::class.java)
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pendingIntent = PendingIntent.getActivity(this,0,targetIntent,0)
        ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)

        try {
            ndef.addDataType("*/*")
        }catch(e:RuntimeException){
            throw e
        }

        filter = arrayOf(ndef)
        techList = arrayOf(arrayOf(NfcA::class.java.name))
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onScheduleSubject(event: ScheduleSubjectEvent){
        binding.viewmodel?.setScheduleSubject(event.scheduleSubject)
        Log.i("MainActivity::EventBusData","${event.scheduleSubject.scheduleName}, ${event.scheduleSubject.roomInfo},${event.scheduleSubject.originId}")
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onUser(event: UserEvent){
        binding.viewmodel?.setUser(event.user)
        Log.i("MainActivity::EventBusData","nameUser:${event.user.nameUser}, id:${event.user.id}")
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onLectureTimeItem(event: LectureTimeItemEvent){
        binding.viewmodel?.setLectureTimeItem(event.lectureTimeItem)
        Log.i("MainActivity::EventBusData","lectureTimeItem.week:${event.lectureTimeItem.week}, lectureTimeItem.timer:${event.lectureTimeItem.time}, lectureTimeItem.date:${event.lectureTimeItem.date}")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
            /**admin페이지로 기능 분리 성공하기 전까지 keep*/
//        val detectedTag:Tag? = intent?.getParcelableExtra(NfcAdapter.EXTRA_TAG)
//        //nfc payload 쓰기
//        val writeValue = "19"
//        val message:NdefMessage = createTagMessage(writeValue)
//        writeTag(message, detectedTag!!)
//
//        //nfc payload 읽기
//        val messages = intent?.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
//        if (messages == null){
//            Log.i("MainActivity.readMsg.result::-----------------------------------------------------------------","Empty messages");
//            return
//        }
//        for (i in messages.indices) {
//            val result = readMsg(messages[i] as NdefMessage)
//            Log.i("MainActivity.readMsg.result::-----------------------------------------------------------------",result);
//        }
    }

    private fun createTagMessage(msg:String):NdefMessage{
        return NdefMessage(NdefRecord.createTextRecord("ID",msg))
    }

    private fun writeTag(message: NdefMessage, tag:Tag) {
        val size = message.toByteArray().size
        try {
            val ndef = Ndef.get(tag)
            if (ndef != null) {
                ndef.connect()
                if (!ndef.isWritable) {
                    Toast.makeText(applicationContext, "can not write NFC tag", Toast.LENGTH_SHORT).show()
                }
                else if (ndef.maxSize < size) {
                    Toast.makeText(applicationContext, "NFC tag size too large", Toast.LENGTH_SHORT).show()
                }
                else{
                    ndef.writeNdefMessage(message)
                    Toast.makeText(applicationContext, "NFC tag is written", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Log.i("MainActivity.writeTag.exception",e.message.toString());
        }
    }

    fun readMsg(mMessage: NdefMessage):String {
        var result = "intial_value"
        val recs = mMessage.records
        for (i in recs.indices) {
            val record = recs[i]
            if (Arrays.equals(record.type, NdefRecord.RTD_TEXT)) {
                val text = record.toString()
                result = text
                Log.i("MainActivity.readMsg",text);
            }
        }
        return result
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
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
