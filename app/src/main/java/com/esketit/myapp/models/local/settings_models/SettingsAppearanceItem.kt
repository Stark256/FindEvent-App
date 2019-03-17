package com.esketit.myapp.models.local.settings_models

class SettingsAppearanceItem(var iconRes: Int, var titleRes: Int): SettingsBaseItem(){

    override fun getType(): SettingItemType = SettingItemType.TYPE_APPEARANCE
}
