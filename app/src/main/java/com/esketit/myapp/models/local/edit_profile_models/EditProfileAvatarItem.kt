package com.esketit.myapp.models.local.edit_profile_models

class EditProfileAvatarItem(val imageUrl: String): EditProfileBaseItem(){
    override fun getType(): EditProfileItemType = EditProfileItemType.TYPE_AVATAR
}
