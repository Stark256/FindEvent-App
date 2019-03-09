package com.esketit.myapp.models.local.settings_models

abstract class SettingsBaseItem{

    enum class SettingItemType(var value: Int){
        TYPE_EMPTY(0),
        TYPE_PROFILE(1)
    }

    abstract fun getType(): SettingItemType

}
