package com.esketit.myapp.models.local.settings_models

import com.esketit.myapp.models.firebase.User

class SettingsProfileItem(val user: User?): SettingsBaseItem(){

    override fun getType(): SettingItemType = SettingItemType.TYPE_PROFILE
}
