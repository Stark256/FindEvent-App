package com.esketit.myapp.models.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp


class User(var id: String = "",
           var name: String = "",
           var email: String = "",
           var avatarImgURL: String = "",
           var latitude: String = "",
           var longitude: String = "",
           @ServerTimestamp var activeTime: Timestamp? = null){

    fun data(): Map<String, Any?>{
        return hashMapOf(
            Pair(Key.idKey.value, id),
            Pair(Key.nameKey.value, name),
            Pair(Key.emailKey.value, email),
            Pair(Key.avatarImgUrlKey.value, avatarImgURL),
            Pair(Key.latitudeKey.value, latitude),
            Pair(Key.longtitudeKey.value, longitude),
            Pair(Key.activeTimeKey.value, activeTime)
            )
    }

    enum class Key(val value: String){
        idKey("id"),
        nameKey("name"),
        emailKey("email"),
        avatarImgUrlKey("avatarImgURL"),
        latitudeKey("latitude"),
        longtitudeKey("longitude"),
        activeTimeKey("activeTime")
    }
}
