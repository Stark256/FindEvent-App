package com.esketit.myapp.view.edit_image_view

import android.content.Context
import android.net.Uri
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.esketit.myapp.R
import kotlinx.android.synthetic.main.view_edit_image.view.*

class EditImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0): RelativeLayout(context, attrs, defStyleAttr){


    private var root: CardView? = null//by lazy { findViewById<CardView>(R.id.cv_avatar_border) }
    private var cardView: CardView? = null//by lazy { findViewById<CardView>(R.id.cv_avatar) }
    private var imageView: ImageView? = null//by lazy { findViewById<ImageView>(R.id.iv_avatar) }
    private var textView: TextView? = null// by lazy { findViewById<TextView>(R.id.tv_text) }

    private lateinit var clickListener: EditImageViewClickListener
    private lateinit var dialogBaseClickListener: EditImageDialogBaseClickListener
    private lateinit var dialogRemoveClickListener: EditImageDialogRemoveClickListener

    private var isRemoveVisible: Boolean = false

    private val paddingZero: Int = context.resources.getDimension(R.dimen.edit_image_view_camera_pading_1).toInt()
    private val paddingCamera: Int = context.resources.getDimension(R.dimen.edit_image_view_camera_pading_2).toInt()
    private val paddingCameraS: Int = context.resources.getDimension(R.dimen.edit_image_view_camera_pading_3).toInt()

    private val rootSmallSize: Int = context.resources.getDimension(R.dimen.edit_image_view_small_border_size).toInt()
    private val rootMediumSize: Int = context.resources.getDimension(R.dimen.edit_image_view_medium_border_size).toInt()
    private val rootBigSize: Int = context.resources.getDimension(R.dimen.edit_image_view_big_border_size).toInt()
    private val rootSmallRadius: Float = context.resources.getDimension(R.dimen.edit_image_view_small_border_radius)
    private val rootMediumRadius: Float = context.resources.getDimension(R.dimen.edit_image_view_medium_border_radius)
    private val rootBigRadius: Float = context.resources.getDimension(R.dimen.edit_image_view_big_border_radius)

    private val avatarSmallSize: Int = context.resources.getDimension(R.dimen.edit_image_view_small_size).toInt()
    private val avatarMediumSize: Int = context.resources.getDimension(R.dimen.edit_image_view_medium_size).toInt()
    private val avatarBigSize: Int = context.resources.getDimension(R.dimen.edit_image_view_big_size).toInt()
    private val avatarSmallRadius: Float = context.resources.getDimension(R.dimen.edit_image_view_small_radius)
    private val avatarMediumRadius: Float = context.resources.getDimension(R.dimen.edit_image_view_medium_radius)
    private val avatarBigRadius: Float = context.resources.getDimension(R.dimen.edit_image_view_big_radius)


    private val bigTextSize: Float = context.resources.getDimension(R.dimen.edit_image_view_big_text_size)
    private val smallTextSize: Float = context.resources.getDimension(R.dimen.edit_image_view_small_text_size)

    //private var clickable: Boolean = true

    init {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.view_edit_image, this, true)

        root = view.cv_avatar_border
        cardView = view.cv_avatar
        imageView = view.iv_avatar
        textView = view.tv_text

        initView()
    }

    private fun initView(){
        if(isClickable) {
            root?.setOnClickListener { showDialog() }
        }

    }

    /*     View Actions     */

    fun loadImage(url: String){
        imageView?.let {
            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_person_placeholder)
                .into(it);
        }

       removeAddImageView()
    }

    fun loadImage(uri: Uri){
        imageView?.let {
            Glide.with(context)
                .load(uri)
                .centerCrop()
                .placeholder(R.drawable.ic_person_placeholder)
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
        imageView?.setPaddingRelative(paddingCamera, paddingCamera, paddingCamera, paddingCameraS)
        textView?.visibility = View.VISIBLE
        setText(R.string.add)
    }

    fun setSmallView(){
        root?.layoutParams?.let {
            it.width = rootSmallSize
            it.height = rootSmallSize
            root?.layoutParams = it
        }
        root?.radius = rootSmallRadius

        cardView?.layoutParams?.let {
            it.width = avatarSmallSize
            it.height = avatarSmallSize
            cardView?.layoutParams = it
        }
        cardView?.radius = avatarSmallRadius

        imageView?.layoutParams?.let {
            it.width = avatarSmallSize
            it.height = avatarSmallSize
            imageView?.layoutParams = it
        }
        textView?.textSize = 10f
    }

    fun setMediumSize() {
        root?.layoutParams?.let {
            it.width = rootMediumSize
            it.height = rootMediumSize
            root?.layoutParams = it
        }
        root?.radius = rootMediumRadius

        cardView?.layoutParams?.let {
            it.width = avatarMediumSize
            it.height = avatarMediumSize
            cardView?.layoutParams = it
        }
        cardView?.radius = avatarMediumRadius

        imageView?.layoutParams?.let {
            it.width = avatarMediumSize
            it.height = avatarMediumSize
            imageView?.layoutParams = it
        }
        textView?.textSize = 14f
    }

    fun setBigView(){
        root?.layoutParams?.let {
            it.width = rootBigSize
            it.height = rootBigSize
            root?.layoutParams = it
        }
        root?.radius = rootBigRadius

        cardView?.layoutParams?.let {
            it.width = avatarBigSize
            it.height = avatarBigSize
            cardView?.layoutParams = it
        }
        cardView?.radius = avatarBigRadius

        imageView?.layoutParams?.let {
            it.width = avatarBigSize
            it.height = avatarBigSize
            imageView?.layoutParams = it
        }
        textView?.textSize = 18f
    }

    private fun removeAddImageView(){
        imageView?.setPadding(paddingZero, paddingZero, paddingZero, paddingZero)
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
