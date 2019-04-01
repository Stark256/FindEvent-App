package com.esketit.myapp.models.local.edit_profile_models

class EditProfileBioItem(var bio: String): EditProfileBaseItem(){
    override fun getType(): EditProfileItemType = EditProfileItemType.TYPE_BIO
}
