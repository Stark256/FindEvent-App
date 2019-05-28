package com.esketit.myapp.ui.main.friends

import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.models.local.friends_models.FriendsRequestsModel

interface FriendsListener {

    interface OnFriendsDataTransfer {
        fun onFriendsNeedRefresh(arr: (ArrayList<User>) -> Unit)
    }

    interface OnRequestDataTransfer {
        fun onRequestNeedRefresh(arr: (ArrayList<FriendsRequestsModel>) -> Unit)
    }

    interface OnSentDataTransfer {
        fun onSentNeedRefresh(arr: (ArrayList<FriendsRequestsModel>) -> Unit)
    }

}
