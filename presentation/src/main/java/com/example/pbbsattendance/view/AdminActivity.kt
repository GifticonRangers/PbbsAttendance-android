package com.example.pbbsattendance.view

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
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.pbbsattendance.MainActivity
import com.example.pbbsattendance.databinding.ActivityAdminBinding
import com.example.pbbsattendance.viewmodel.AdminViewModel
import java.util.*

class AdminActivity: AppCompatActivity()  {
    private var _binding : ActivityAdminBinding? = null
    private val binding get() = _binding!!
    private var nfcAdapter: NfcAdapter? = null
    private lateinit var pendingIntent: PendingIntent
    private lateinit var ndef: IntentFilter
    private lateinit var filter: Array<IntentFilter>
    private lateinit var techList: Array<Array<String>>
    private var nfcPayloadIndex = 1

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        _binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)
        binding.viewModel?.nfcPayload?.observe(this, androidx.lifecycle.Observer {
            it.forEach {
                binding.nfcList.text =  binding.nfcList.text.toString() + it + "\n"
            }
        })
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val targetIntent = Intent(this, MainActivity::class.java)
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pendingIntent = PendingIntent.getActivity(this,0,targetIntent,0)

    }

    override fun onResume() {
        super.onResume()
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent?.action == NfcAdapter.ACTION_NDEF_DISCOVERED){
            val detectedTag: Tag? = intent?.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            //nfc payload 쓰기
            val writeValue = nfcPayloadIndex.toString()
            val message:NdefMessage = createTagMessage(writeValue)
            writeTag(message, detectedTag!!)

            //nfc payload 읽기
            val messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            if (messages == null){
                return
            }
            for (i in messages.indices) {
                val result = readMsg(messages[i] as NdefMessage)
                Log.i("MainActivity.readMsg.result::",result)
                binding.viewModel?.addNfcPayloadList(result)
            }
        }
    }

    private fun createTagMessage(msg:String):NdefMessage{
        return NdefMessage(NdefRecord.createTextRecord("ID",msg))
    }

    private fun writeTag(message: NdefMessage, tag:Tag) {
        nfcPayloadIndex += 1
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}