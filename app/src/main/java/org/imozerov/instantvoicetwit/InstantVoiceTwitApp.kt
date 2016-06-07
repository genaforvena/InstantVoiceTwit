package org.imozerov.instantvoicetwit

import android.app.Application
import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.tweetcomposer.TweetComposer
import io.fabric.sdk.android.Fabric

/**
 * Created by imozerov on 07.06.16.
 */
class InstantVoiceTwitApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val authConfig = TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET)
        Fabric.with(this, Twitter(authConfig), TweetComposer())
    }

    companion object {
        private val TWITTER_KEY = "cYHHGTOxM0gJryPbQHezfLgrI"
        private val TWITTER_SECRET = "ERHiRduKs12SaHEWA9YBbSc1427LlyuxgQodrlYHgJFigL7AlJ"
    }
}