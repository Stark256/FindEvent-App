package com.esketit.myapp.models.firebase


class User(var id: String = "",
           var name: String = "",
           var email: String = ""){

    fun data(): Map<String, Any>{
        return hashMapOf(
            Pair(Key.idKey.value, id),
            Pair(Key.nameKey.value, name),
            Pair(Key.emailKey.value, email)
            )
    }

    private enum class Key(val value: String){
        idKey("id"),
        nameKey("name"),
        emailKey("email")
    }
}
