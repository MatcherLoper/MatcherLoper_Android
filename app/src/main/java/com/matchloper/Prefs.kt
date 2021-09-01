package com.matchloper

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(context: Context) {
    private val prefNm = "mPref"
    private val prefs = context.getSharedPreferences(prefNm, MODE_PRIVATE)

    var userId : Int
    get() = prefs.getInt("userId",0)
    set(value) {
        prefs.edit().putInt("userId",value).apply()
    }

    var deviceToken : String?
    get() = prefs.getString("deviceToken",null)
    set(value) {
        prefs.edit().putString("deviceToken",value).apply()
    }

    var userToken : String?
    get() = prefs.getString("userToken",null)
    set(value) {
        prefs.edit().putString("userToken",value).apply()
    }
}