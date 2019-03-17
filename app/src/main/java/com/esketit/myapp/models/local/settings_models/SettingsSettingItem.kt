package com.esketit.myapp.models.local.settings_models

class SettingsSettingItem(var iconRes: Int, var titleRes: Int): SettingsBaseItem(){

    override fun getType(): SettingItemType = SettingItemType.TYPE_SETTING
}
