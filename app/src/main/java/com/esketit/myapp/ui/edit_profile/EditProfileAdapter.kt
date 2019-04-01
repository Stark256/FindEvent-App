package com.esketit.myapp.ui.edit_profile

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.models.local.edit_profile_models.*
import com.esketit.myapp.ui.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_edit_profile_avatar.view.*
import kotlinx.android.synthetic.main.item_edit_profile_bio.view.*
import kotlinx.android.synthetic.main.item_edit_profile_location.view.*
import kotlinx.android.synthetic.main.item_edit_profile_logout.view.*
import kotlinx.android.synthetic.main.item_edit_profile_name.view.*
import kotlinx.android.synthetic.main.item_edit_profile_tooltip_text.view.*

class EditProfileAdapter(val context: Context, val listener: EditProfileClickListener): BaseRecyclerAdapter<EditProfileBaseItem, RecyclerView.ViewHolder>(){


    init {
        replaceAll(arrayListOf(
            EditProfileAvatarItem(""),   // index 0
            EditProfileTooltipTextItem(""),   // index 1
            EditProfileEmptyItem(),   // index 2
            EditProfileNameItem(""),   // index 3
            EditProfileTooltipTextItem(""),   // index 4
            EditProfileEmptyItem(),   // index 5
            EditProfileBioItem(""),   // index 6
            EditProfileTooltipTextItem(""),   // index 7
            EditProfileEmptyItem(),   // index 8
            EditProfileLocationItem(""),   // index 9
            EditProfileTooltipTextItem(""),   // index 10
            EditProfileEmptyItem(),   // index 11
            EditProfileLogoutItem()   // index 12
        ))
    }


    override fun getItemViewType(position: Int): Int = getItem(position).getType().value

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return when(type){
            EditProfileBaseItem.EditProfileItemType.TYPE_AVATAR.value -> { EditProfileAvatarViewHolder(LayoutInflater.from(context).inflate(R.layout.item_edit_profile_avatar, parent, false)) }
            EditProfileBaseItem.EditProfileItemType.TYPE_BIO.value -> { EditProfileBioViewHolder(LayoutInflater.from(context).inflate(R.layout.item_edit_profile_bio, parent, false)) }
            EditProfileBaseItem.EditProfileItemType.TYPE_NAME.value -> { EditProfileNameViewHolder(LayoutInflater.from(context).inflate(R.layout.item_edit_profile_name, parent, false)) }
            EditProfileBaseItem.EditProfileItemType.TYPE_TOOLTIP_TEXT.value -> { EditProfileTooltipTextViewHolder(LayoutInflater.from(context).inflate(R.layout.item_edit_profile_tooltip_text, parent, false)) }
            EditProfileBaseItem.EditProfileItemType.TYPE_LOGOUT.value -> { EditProfileLogoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_edit_profile_logout, parent, false)) }
            EditProfileBaseItem.EditProfileItemType.TYPE_LOCATION.value -> { EditProfileLocationViewHolder(LayoutInflater.from(context).inflate(R.layout.item_edit_profile_location, parent, false)) }
            else -> EditProfileEmptyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_edit_profile_empty, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(item.getType()){
            EditProfileBaseItem.EditProfileItemType.TYPE_EMPTY -> {}
            EditProfileBaseItem.EditProfileItemType.TYPE_TOOLTIP_TEXT -> {}
            EditProfileBaseItem.EditProfileItemType.TYPE_AVATAR -> {}
            EditProfileBaseItem.EditProfileItemType.TYPE_NAME -> {}
            EditProfileBaseItem.EditProfileItemType.TYPE_BIO -> {}
            EditProfileBaseItem.EditProfileItemType.TYPE_LOCATION -> {}
            EditProfileBaseItem.EditProfileItemType.TYPE_LOGOUT -> {
                (holder as EditProfileLogoutViewHolder).btnLogOut.setOnClickListener { listener.logOutPressed() }
            }
        }
    }


    private class EditProfileEmptyViewHolder(v: View): RecyclerView.ViewHolder(v){}

    private class EditProfileLogoutViewHolder(v: View): RecyclerView.ViewHolder(v){
        val btnLogOut = v.btn_edit_profile_logout
    }

    private class EditProfileTooltipTextViewHolder(v: View): RecyclerView.ViewHolder(v){
        val tooltipText = v.tv_edit_profile_tooltip
    }

    private class EditProfileAvatarViewHolder(v: View): RecyclerView.ViewHolder(v){
        val avatar = v.eiv_edit_profile_avatar
    }

    private class EditProfileNameViewHolder(v: View): RecyclerView.ViewHolder(v){
        val ti_bio = v.ti_edit_profile_name
        val et_bio = v.et_edit_profile_name
    }

    private class EditProfileBioViewHolder(v: View): RecyclerView.ViewHolder(v){
        val ti_bio = v.ti_edit_profile_bio
        val et_bio = v.et_edit_profile_bio
    }

    private class EditProfileLocationViewHolder(v: View): RecyclerView.ViewHolder(v){
        val locationText = v.tv_edit_profile_location
    }







    interface EditProfileClickListener{
        fun logOutPressed()
        fun locationPressed()
    }

}
