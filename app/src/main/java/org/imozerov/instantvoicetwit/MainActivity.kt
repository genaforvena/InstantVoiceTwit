package org.imozerov.instantvoicetwit

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.tweetcomposer.TweetComposer
import org.imozerov.instantvoicetwit.login.LoginActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    var txtSpeechInput: TextView? = null
    var sendTweetButton: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Twitter.getSessionManager().activeSession == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_main)

        txtSpeechInput = findViewById(R.id.txtSpeechInput) as TextView
        sendTweetButton = findViewById(R.id.sendTweet)
        sendTweetButton!!.setOnClickListener { sendTweet() }
        findViewById(R.id.btnSpeak)!!.setOnClickListener { promptSpeechInput() }
    }

    private fun sendTweet() {
        TweetComposer.Builder(this).text(txtSpeechInput!!.text.toString()).show()
        resetInput()
    }

    private fun resetInput() {
        txtSpeechInput!!.text = ""
        sendTweetButton!!.visibility = View.GONE
    }

    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext,
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    txtSpeechInput!!.text = result[0]
                    sendTweetButton!!.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object {
        private val REQ_CODE_SPEECH_INPUT = 11342
    }
}
