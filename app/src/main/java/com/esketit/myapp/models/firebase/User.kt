package com.esketit.myapp.models.firebase


class User(var id: String = "",
           var name: String = "",
           var email: String = "",
           var avatarImgURL: String = "",
           var latitude: String = "",
           var longtitude: String = ""){

    fun data(): Map<String, Any>{
        return hashMapOf(
            Pair(Key.idKey.value, id),
            Pair(Key.nameKey.value, name),
            Pair(Key.emailKey.value, email),
            Pair(Key.avatarImgUrlKey.value, avatarImgURL),
            Pair(Key.latitudeKey.value, latitude),
            Pair(Key.longtitudeKey.value, longtitude)
            )
    }

    private enum class Key(val value: String){
        idKey("id"),
        nameKey("name"),
        emailKey("email"),
        avatarImgUrlKey("avatarImgURL"),
        latitudeKey("latitude"),
        longtitudeKey("longtitude")
    }
}
