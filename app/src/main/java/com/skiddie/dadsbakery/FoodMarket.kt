package com.skiddie.dadsbakery

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager

class FoodMarket : MultiDexApplication() {

    companion object {
        lateinit var instance: com.skiddie.dadsbakery.FoodMarket

        fun getApp(): com.skiddie.dadsbakery.FoodMarket {
            return com.skiddie.dadsbakery.FoodMarket.Companion.instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        com.skiddie.dadsbakery.FoodMarket.Companion.instance = this

    }

    fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token: String) {
        getPreferences().edit().putString("PREFERENCES_TOKEN", token).apply()
        com.skiddie.dadsbakery.network.HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken(): String? {
        return getPreferences().getString("PREFERENCES_TOKEN", null)
    }

    fun setUser(user: String) {
        getPreferences().edit().putString("PREFERENCES_USER", user).apply()
    }

    fun getUser(): String? {
        return getPreferences().getString("PREFERENCES_USER", null)
    }

}