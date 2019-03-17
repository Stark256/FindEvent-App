package com.esketit.myapp.models.local.settings_models

abstract class SettingsBaseItem{

    enum class SettingItemType(var value: Int){
        TYPE_EMPTY(0),
        TYPE_PROFILE(1),
        TYPE_NOTIFICATIONS(2),
        TYPE_APPEARANCE(3),
        TYPE_LANGUAGE(4),
        TYPE_BUG_REPORT(5),
        TYPE_ABOUT_US(6),
    }

    abstract fun getType(): SettingItemType

}
