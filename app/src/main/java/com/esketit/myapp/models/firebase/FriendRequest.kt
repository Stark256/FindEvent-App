package com.esketit.myapp.models.firebase

class FriendRequest(
    var id: String = "",
    var receiver: String = "",
    var sender: String = "",
    var status: String = FriendRequestStatus.PENDING.value) {


    fun data(): Map<String, Any?>{
        return hashMapOf(
            Pair(Key.idKey.value, id),
            Pair(Key.receiverKey.value, receiver),
            Pair(Key.senderKey.value, sender),
            Pair(Key.statusKey.value, status)
        )
    }


    enum class Key(val value: String){
        idKey("id"),
        receiverKey("receiver"),
        senderKey("sender"),
        statusKey("status")
    }
}

enum class FriendRequestStatus(val value: String) {
    ACCEPTED("accepted"),
    PENDING("pending"),
    DECLINED("declined")
}