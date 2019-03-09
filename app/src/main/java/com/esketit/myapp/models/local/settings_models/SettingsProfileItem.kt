package com.esketit.myapp.models.local.settings_models

class SettingsProfileItem(val data: Any?): SettingsBaseItem(){

    override fun getType(): SettingItemType = SettingItemType.TYPE_PROFILE
}
