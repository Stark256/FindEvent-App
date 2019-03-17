package com.esketit.myapp.models.local.settings_models

class SettingsBugReportItem(var iconRes: Int, var titleRes: Int): SettingsBaseItem() {

    override fun getType(): SettingItemType = SettingItemType.TYPE_BUG_REPORT
}
