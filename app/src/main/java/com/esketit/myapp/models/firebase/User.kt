package com.esketit.myapp.models.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ServerTimestamp

class User(var id: String = "",
           var name: String = "",
           var email: String = "",
           var avatarImgURL: String = "",
           var cordinate: GeoPoint? = null,
           var description: String = "",
           @ServerTimestamp var activeTime: Timestamp? = null){

    fun data(): Map<String, Any?>{
        return hashMapOf(
            Pair(Key.idKey.value, id),
            Pair(Key.nameKey.value, name),
            Pair(Key.emailKey.value, email),
            Pair(Key.avatarImgUrlKey.value, avatarImgURL),
            Pair(Key.cordinateKey.value, cordinate),
            Pair(Key.activeTimeKey.value, activeTime),
            Pair(Key.descriptionKey.value, description)
            )
    }

    enum class Key(val value: String){
        idKey("id"),
        nameKey("name"),
        emailKey("email"),
        avatarImgUrlKey("avatarImgURL"),
        cordinateKey("cordinate"),
        activeTimeKey("activeTime"),
        descriptionKey("description")
    }
}
