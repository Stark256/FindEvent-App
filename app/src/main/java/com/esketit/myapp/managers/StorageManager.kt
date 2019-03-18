package com.esketit.myapp.managers

import android.content.Context

class StorageManager: PreferenceHelper(){
    companion object {
        private const val LANGUAGE_KEY = "language"
    }

    fun setLanguage(language: String){
        storeValue(Injector.application!!, LANGUAGE_KEY, language)
    }

    fun getLanguage(): String? = getStringValue(Injector.application!!, LANGUAGE_KEY)

    enum class Language(val value: String){
        ENGLISH("en"), UKRAINIAN("ua")
    }
}
