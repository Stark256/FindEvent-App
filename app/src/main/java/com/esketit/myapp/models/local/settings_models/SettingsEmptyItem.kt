package com.esketit.myapp.models.local.settings_models

class SettingsEmptyItem(): SettingsBaseItem(){

    override fun getType(): SettingItemType = SettingItemType.TYPE_EMPTY
}
