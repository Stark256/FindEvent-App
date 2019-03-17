package com.esketit.myapp.models.local.settings_models

class SettingsAboutUsItem(var iconRes: Int, var titleRes: Int): SettingsBaseItem(){

    override fun getType(): SettingItemType = SettingItemType.TYPE_ABOUT_US
}
