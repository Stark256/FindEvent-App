package com.esketit.myapp.ui.main.friends.friendsTabFragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.ui.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_friend_tab.view.*

class FriendsTabAdapter : BaseRecyclerAdapter<User, RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        return FriendViewHolder(LayoutInflater.from(context).inflate(R.layout.item_friend_tab, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as? FriendViewHolder)?.let {
            it.bindView(item)
        }
    }

    private class FriendViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image = v.eiv_friend_tab_avatar
        val name = v.tv_friend_tab_name

        fun bindView(user: User) {
            image.setSmallView()
            image.loadImage(user.avatarImgURL)
            name.text = user.name
        }
    }
}
