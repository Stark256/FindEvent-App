package com.esketit.myapp.models.local.edit_profile_models

class EditProfileNameItem(var userName: String): EditProfileBaseItem(){
    override fun getType(): EditProfileItemType = EditProfileItemType.TYPE_NAME
}
