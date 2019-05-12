package com.example.tickettap.NFC

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tickettap.R

//                    Tutorial Followed For Help
//Agency, J. (2019). A Complete Guide To Implementing NFC in a Kotlin Application. [online]
// JetRuby. Available at: https://expertise.jetruby.com/a-complete-guide-to-implementing-nfc-in-a-kotlin-application-5a94c5baf4dd
// [Accessed 1 Mar. 2019].

class NFCSenderActivity : AppCompatActivity(), OutgoingNFC.NfcActivity {

    private lateinit var tvOutcomingMessage: TextView
    private lateinit var etOutcomingMessage: EditText
    private lateinit var btnSetOutcomingMessage: Button

    private var nfcAdapter: NfcAdapter? = null

    private lateinit var outcomingNfcCallback: OutgoingNFC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)

        this.nfcAdapter = NfcAdapter.getDefaultAdapter(this)?.let { it }

        if (!nfcAdapter?.isEnabled!!) {
            Toast.makeText(
                    this,
                    "Please Turn NFC On",
                    Toast.LENGTH_SHORT
            ).show()
        }

        initViews()
        this.outcomingNfcCallback = OutgoingNFC(this)
        this.nfcAdapter?.setOnNdefPushCompleteCallback(outcomingNfcCallback, this)
        this.nfcAdapter?.setNdefPushMessageCallback(outcomingNfcCallback, this)
    }

    private fun initViews() {
        this.tvOutcomingMessage = findViewById(R.id.messageSent)
        this.etOutcomingMessage = findViewById(R.id.messageToSend)
        this.btnSetOutcomingMessage = findViewById(R.id.submitNfcBtn)
        this.btnSetOutcomingMessage.setOnClickListener { setOutGoingMessage() }
    }

    override fun onNewIntent(intent: Intent) {
        this.intent = intent
    }

    private fun setOutGoingMessage() {
        val outMessage = this.etOutcomingMessage.text.toString()
        this.tvOutcomingMessage.text = outMessage
    }

    override fun getOutgoingMessage(): String =
            this.tvOutcomingMessage.text.toString()


    override fun signalResult() {
        runOnUiThread {
            Toast.makeText(this, R.string.messageSendingComplete, Toast.LENGTH_SHORT).show()
        }
    }
}
