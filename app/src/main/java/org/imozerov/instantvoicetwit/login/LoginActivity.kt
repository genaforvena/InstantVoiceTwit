package org.imozerov.instantvoicetwit.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import org.imozerov.instantvoicetwit.MainActivity
import org.imozerov.instantvoicetwit.R

class LoginActivity : AppCompatActivity() {
    private var loginButton: TwitterLoginButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.twitter_login_button) as TwitterLoginButton
        loginButton!!.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                Log.i(TAG, "Logged in with twitter")
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }

            override fun failure(exception: TwitterException) {
                Log.e(TAG, "Login with Twitter failure", exception)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        loginButton!!.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private val TAG = "LoginActivity"
    }
}
