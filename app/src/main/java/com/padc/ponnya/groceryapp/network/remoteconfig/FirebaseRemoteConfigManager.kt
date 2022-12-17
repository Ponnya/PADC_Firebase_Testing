package com.padc.ponnya.groceryapp.network.remoteconfig

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.padc.ponnya.groceryapp.utils.APP_BAR_NAME_CONFIG
import com.padc.ponnya.groceryapp.utils.APP_BAR_VERSION_CONFIG

object FirebaseRemoteConfigManager {
    private val remoteConfig = Firebase.remoteConfig

    init {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    fun setUpRemoteConfigWithDefaultValue() {
        val defaultValues: Map<String, Any> = mapOf(
            APP_BAR_NAME_CONFIG to "Grocery-App",
            APP_BAR_VERSION_CONFIG to 0
        )
        remoteConfig.setDefaultsAsync(defaultValues)
    }

    fun fetchRemoteConfig() {
        remoteConfig.fetch()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Firebase", "Firebase Remote Config fetch success")
                    remoteConfig.activate().addOnCompleteListener {
                        Log.d("Firebase", "Firebase Remote Config activated")
                    }
                } else {
                    Log.d("Firebase", "Firebase Remote Config fetch failed")
                }
            }
    }

    fun getToolbarName(): String {
        return remoteConfig.getValue(APP_BAR_NAME_CONFIG).asString()
    }

    fun getVersion(): Int {
        return remoteConfig.getValue(APP_BAR_VERSION_CONFIG).asLong().toInt()
    }
}