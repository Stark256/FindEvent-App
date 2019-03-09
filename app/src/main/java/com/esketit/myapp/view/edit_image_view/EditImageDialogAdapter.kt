package com.esketit.myapp.view.edit_image_view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.esketit.myapp.R
import kotlinx.android.synthetic.main.item_edit_image_dialog_adapter.view.*

class EditImageDialogAdapter(var array: Array<EditImageDialogItem>, val context: Context): BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
           val view = LayoutInflater.from(parent?.context)
               .inflate(R.layout.item_edit_image_dialog_adapter, parent, false)

        val item = array[position]
        val title = view.tv_dialog_item_title

        title.text = item.title
        if(item.isRemove){
            title.setTextColor(ContextCompat.getColor(context, R.color.red))
        }else{
            // TODO customize title in theme manager
        }


        return view
    }

    override fun getItem(position: Int): Any {
        return array[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return array.size
    }
}
