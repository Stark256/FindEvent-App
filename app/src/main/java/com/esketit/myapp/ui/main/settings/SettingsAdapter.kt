package com.esketit.myapp.ui.main.settings

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.models.local.settings_models.SettingsBaseItem
import com.esketit.myapp.models.local.settings_models.SettingsEmptyItem
import com.esketit.myapp.models.local.settings_models.SettingsProfileItem
import com.esketit.myapp.ui.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_settings_profile.view.*

class SettingsAdapter: BaseRecyclerAdapter<SettingsBaseItem, RecyclerView.ViewHolder>(){

    override fun getItemViewType(position: Int): Int = getItem(position).getType().value

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return when(type){
            SettingsBaseItem.SettingItemType.TYPE_PROFILE.value -> { ProfileViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_settings_profile, parent, false)) }
            SettingsBaseItem.SettingItemType.TYPE_EMPTY.value -> { EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_settings_empty, parent,false)) }
            else -> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_settings_empty, parent,false))

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(item.getType()){
            SettingsBaseItem.SettingItemType.TYPE_PROFILE -> {
                val profile = item as SettingsProfileItem
                (holder as ProfileViewHolder).bindView(profile.data as User)
            }
            SettingsBaseItem.SettingItemType.TYPE_EMPTY -> { }
        }
    }


    private class ProfileViewHolder(v: View): RecyclerView.ViewHolder(v){
        val image = v.eiv_profile_avatar
        val name = v.tv_profile_name
        val email = v.tv_profile_email

        fun bindView(item: User){
            image.loadImage(item.avatarImgURL)
            name.text = item.name
            email.text = item.email
        }
    }

    private class EmptyViewHolder(v: View): RecyclerView.ViewHolder(v){}

    interface SettingsClickListener{
        fun onItemPressed(settingsItemType: Int)
    }
}
