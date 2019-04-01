package com.esketit.myapp.models.local.edit_profile_models

class EditProfileTooltipTextItem(val text: String): EditProfileBaseItem(){
    override fun getType(): EditProfileItemType = EditProfileItemType.TYPE_TOOLTIP_TEXT
}
