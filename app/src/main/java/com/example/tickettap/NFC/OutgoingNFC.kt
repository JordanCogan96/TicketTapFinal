package com.example.tickettap.NFC

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;


//                    Tutorial Followed For Help
//Agency, J. (2019). A Complete Guide To Implementing NFC in a Kotlin Application. [online]
// JetRuby. Available at: https://expertise.jetruby.com/a-complete-guide-to-implementing-nfc-in-a-kotlin-application-5a94c5baf4dd
// [Accessed 1 Mar. 2019].

class OutgoingNFC(
        private val nfcActivity: NfcActivity
) :
        NfcAdapter.CreateNdefMessageCallback,
        NfcAdapter.OnNdefPushCompleteCallback {

    override fun createNdefMessage(event: NfcEvent): NdefMessage {
        val outString = nfcActivity.getOutgoingMessage()

        with(outString) {
            val outBytes = this.toByteArray()
            val outRecord = NdefRecord.createMime(MIME_TEXT_PLAIN, outBytes)
            return NdefMessage(outRecord)
        }
    }

    override fun onNdefPushComplete(event: NfcEvent) {
        nfcActivity.signalResult()
    }

    interface NfcActivity {
        fun getOutgoingMessage(): String

        fun signalResult()
    }
}
