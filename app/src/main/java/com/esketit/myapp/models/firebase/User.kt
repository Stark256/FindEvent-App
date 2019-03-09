package com.esketit.myapp.models.firebase


class User(var id: String = "",
           var name: String = "",
           var email: String = "",
           var avatarImgURL: String = "",
           var latitude: String = "",
           var longitude: String = "",
           var isOnline: Boolean = false){

    fun data(): Map<String, Any>{
        return hashMapOf(
            Pair(Key.idKey.value, id),
            Pair(Key.nameKey.value, name),
            Pair(Key.emailKey.value, email),
            Pair(Key.avatarImgUrlKey.value, avatarImgURL),
            Pair(Key.latitudeKey.value, latitude),
            Pair(Key.longtitudeKey.value, longitude),
            Pair(Key.isOnlineKey.value, isOnline)
            )
    }

    enum class Key(val value: String){
        idKey("id"),
        nameKey("name"),
        emailKey("email"),
        avatarImgUrlKey("avatarImgURL"),
        latitudeKey("latitude"),
        longtitudeKey("longitude"),
        isOnlineKey("isOnline")
    }
}
