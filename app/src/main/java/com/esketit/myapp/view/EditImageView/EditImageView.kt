package com.esketit.myapp.view.EditImageView

import android.content.Context
import android.net.Uri
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.esketit.myapp.R

class EditImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0): RelativeLayout(context, attrs, defStyleAttr){


    private val root: CardView? by lazy { findViewById<CardView>(R.id.cv_avatar) }
    private val imageView: ImageView? by lazy { findViewById<ImageView>(R.id.iv_avatar) }
    private val textView: TextView? by lazy { findViewById<TextView>(R.id.tv_text) }

    private lateinit var clickListener: EditImageViewClickListener
    private lateinit var dialogBaseClickListener: EditImageDialogBaseClickListener
    private lateinit var dialogRemoveClickListener: EditImageDialogRemoveClickListener

    private var isRemoveVisible: Boolean = false

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_edit_image, this, true)

        initView()
    }

    private fun initView(){
        root?.setOnClickListener { showDialog() }

    }

    /*     View Actions     */

    fun loadImage(url: String){
        // TODO make image loading with glade or piccasso
        imageView?.let {
            Glide.with(context)
                .load(url)
                .centerCrop()
                /*.placeholder(R.drawable.loading_spinner)*/
                .into(it);
        }

       removeAddImageView()
    }

    fun loadImage(uri: Uri){
        // TODO make image loading with glade or piccasso
        imageView?.let {
            Glide.with(context)
                .load(uri)
                .centerCrop()
                /*.placeholder(R.drawable.loading_spinner)*/
                .into(it);
        }
        removeAddImageView()
    }

    fun setText(text: String){
        this.textView?.text = text
    }

    fun setText(@StringRes resText: Int){
        this.textView?.setText(resText)
    }

    fun isRemoveVisible(isRemoveVisible: Boolean){
        this.isRemoveVisible = isRemoveVisible
    }

    fun setAddImageView(){
        imageView?.let {
            Glide.with(context)
                .load(context.getDrawable(R.drawable.ic_camera))
                .centerCrop()
                /*.placeholder(R.drawable.loading_spinner)*/
                .into(it);
        }
        imageView?.setPaddingRelative(64, 64, 64, 80)
        textView?.visibility = View.VISIBLE
        setText(R.string.add)
    }

    private fun removeAddImageView(){
        imageView?.setPadding(0, 0, 0, 0)
        textView?.visibility = View.GONE
    }

    private fun showDialog(){
        val arrayList = getAdapterItems()
        val adapter = EditImageDialogAdapter(arrayList, context)

        AlertDialog.Builder(context)
            .setAdapter(adapter){dialog, which ->
                val item = arrayList[which]
                when(item.id){
                    EditImageDialogItemID.ID_CAMERA.value -> { onCameraPressed() }
                    EditImageDialogItemID.ID_GALARY.value -> { onGalaryPressed() }
                    EditImageDialogItemID.ID_REMOVE.value -> { onRemovePressed() }
                }
            }
            .setNegativeButton(resources.getString(R.string.cancel), { dialog, _ -> dialog.dismiss() })
            .show()

    }


    private fun getAdapterItems(): Array<EditImageDialogItem>{
        return if(!isRemoveVisible)
                arrayOf(EditImageDialogItem(EditImageDialogItemID.ID_CAMERA.value,
                    context.getString(R.string.dialog_camera), false),
                    EditImageDialogItem(EditImageDialogItemID.ID_GALARY.value,
                        context.getString(R.string.dialog_galary), false))
            else
                arrayOf(EditImageDialogItem(EditImageDialogItemID.ID_CAMERA.value,
                    context.getString(R.string.dialog_camera), false),
                    EditImageDialogItem(EditImageDialogItemID.ID_GALARY.value,
                        context.getString(R.string.dialog_galary), false),
                    EditImageDialogItem(EditImageDialogItemID.ID_REMOVE.value,
                        context.getString(R.string.dialog_remove), true))
    }

    /*     EditImageViewClickListener     */

    fun setEditImageViewClickListener(clickListener: EditImageViewClickListener){
        this.clickListener = clickListener
    }

    private fun editImageViewPressed(){
        if(::clickListener.isInitialized){
            clickListener.editImageViewPressed()
        }
    }

    /*     EditImageDialogBaseClickListener     */

    fun setDialogBaseCliclListener(dialogBaseClickListener: EditImageDialogBaseClickListener){
        this.dialogBaseClickListener = dialogBaseClickListener
    }

    private fun onGalaryPressed(){
        if(::dialogBaseClickListener.isInitialized){
            dialogBaseClickListener.onGalaryPressed()
        }
    }

    private fun onCameraPressed(){
        if(::dialogBaseClickListener.isInitialized){
            dialogBaseClickListener.onCameraPressed()
        }
    }

    /*     EditImageDialogRemoveClickListener     */

    fun setDialogRemoveCliclListener(dialogRemoveClickListener: EditImageDialogRemoveClickListener){
        this.dialogRemoveClickListener = dialogRemoveClickListener
    }

    private fun onRemovePressed(){
        if(::dialogRemoveClickListener.isInitialized){
            dialogRemoveClickListener.onRemovePressed()
                // TODO show placeholder
        }
    }


}
