package org.imozerov.instantvoicetwit.login

import android.content.SharedPreferences

/**
 * Created by imozerov on 07.06.16.
 */
private val TWITTER_KEY = "cYHHGTOxM0gJryPbQHezfLgrI";
private val TWITTER_SECRET = "ERHiRduKs12SaHEWA9YBbSc1427LlyuxgQodrlYHgJFigL7AlJ";

private val KEY_AUTH_TOKEN = "org.imozerov.auth"

fun SharedPreferences.isLoggedIn() : Boolean {
    return getString(KEY_AUTH_TOKEN, "").isNotBlank()
}