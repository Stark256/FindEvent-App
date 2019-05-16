package com.esketit.myapp.managers

import android.content.Context

class StorageManager: PreferenceHelper(){
    companion object {
        private const val LANGUAGE_KEY = "language"
        private const val LOCATION_ENABLED_KEY = "location_enabled_key"
    }

    fun setLocationEnabled(isEnabled: Boolean) {
        storeBoolean(Injector.application!!, LOCATION_ENABLED_KEY, isEnabled)
    }

    fun getLocationEnabled() : Boolean = getBooleanValue(Injector.application!!, LOCATION_ENABLED_KEY)

    fun setLanguage(language: String){
        storeValue(Injector.application!!, LANGUAGE_KEY, language)
    }

    fun getLanguage(): String? = getStringValue(Injector.application!!, LANGUAGE_KEY)

    enum class Language(val value: String){
        ENGLISH("en"), UKRAINIAN("ua")
    }
}
