package com.esketit.myapp.models.local.edit_profile_models

class EditProfileLocationItem(var location: String): EditProfileBaseItem(){
    override fun getType(): EditProfileItemType = EditProfileItemType.TYPE_LOCATION
}
