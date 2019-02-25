package com.esketit.myapp.view.EditImageView

import android.content.Context
import android.net.Uri
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.esketit.myapp.R
import de.hdodenhof.circleimageview.CircleImageView

class EditImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0): RelativeLayout(context, attrs, defStyleAttr){


    private val root: RelativeLayout? by lazy { findViewById<RelativeLayout>(R.id.rl_edit_image_root) }
    private val baseContainer: RelativeLayout? by lazy { findViewById<RelativeLayout>(R.id.rl_base_container) }
    private val circleImageView: CircleImageView? by lazy { findViewById<CircleImageView>(R.id.civ_avatar) }
    private val textView: TextView? by lazy { findViewById<TextView>(R.id.tv_text) }

    private lateinit var clickListener: EditImageViewClickListener
    private lateinit var dialogBaseClickListener: EditImageDialogBaseClickListener
    private lateinit var dialogRemoveClickListener: EditImageDialogRemoveClickListener

    private var isImageLoaded: Boolean = false
    private var isRemoveVisible: Boolean = false

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_edit_image, this, true)

        initView()
    }

    private fun initView(){
        baseContainer?.setOnClickListener { showDialog() }
        setText(R.string.add)
    }

    /*     View Actions     */

    fun loadImage(uri: Uri){
        // TODO make image loading with glade or piccasso

        isImageLoaded = true
        baseContainer?.visibility = View.GONE
        circleImageView?.setOnClickListener { showDialog() }
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
        return if(isRemoveVisible)
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
            dialogBaseClickListener.onCameraPressed()
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
            isImageLoaded = false
                // TODO show placeholder
        }
    }


}
