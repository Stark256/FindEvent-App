package com.esketit.myapp.models.local.edit_profile_models

abstract class EditProfileBaseItem {

    enum class EditProfileItemType(var value: Int){
        TYPE_EMPTY(0),
        TYPE_TOOLTIP_TEXT(1),
        TYPE_AVATAR(2),
        TYPE_NAME(3),
        TYPE_BIO(4),
        TYPE_LOCATION(5),
        TYPE_LOGOUT(6)
    }

    abstract fun getType(): EditProfileItemType
}
