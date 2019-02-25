package com.esketit.myapp.ui.sing_up

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.view.View
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.BaseActivity
import com.esketit.myapp.util.FieldsValidatorUtil
import com.esketit.myapp.util.PermissionManager
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.ByteArrayOutputStream

class SignUpActivity : BaseActivity() {

    private val RESULT_CAMERA = 15
    private val RESULT_GALARY = 25

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_sing_up.setOnClickListener { btnPressed() }

//        iv_sign_up_avatar.setOnClickListener { cameraPressed() }

        customizeView()
    }

    private fun customizeView(){
        Injector.themesManager.customizeButton(this, btn_sing_up)
        Injector.themesManager.customizeTextInputEditText(this, et_sign_up_email)
        Injector.themesManager.customizeTextInputEditText(this, et_sign_up_name)
        Injector.themesManager.customizeTextInputEditText(this, et_sign_up_pass)
        Injector.themesManager.customizeTextInputLayout(this, ti_sign_up_email)
        Injector.themesManager.customizeTextInputLayout(this, ti_sign_up_name)
        Injector.themesManager.customizeTextInputLayout(this, ti_sign_up_pass)
    }

    private fun btnPressed(){
        if(fieldValidation()) {
            Injector.emailAuth.signUp(et_sign_up_email.text.toString(), et_sign_up_pass.text.toString(), et_sign_up_name.text.toString(), { response ->
                if (response.success) {
                    setResult(Activity.RESULT_OK, Intent())
                    finish()
                } else {
                    showError(response.localizedMessage)
                }
            })
        }
    }

    private fun fieldValidation(): Boolean{
        var isValid = true

        if(checkUserName()) isValid = false
        if(checkEmail()) isValid = false
        if(checkPass()) isValid = false

        return  isValid
    }

    private fun checkUserName(): Boolean{
        setError(ti_sign_up_name, FieldsValidatorUtil.isEmpty(et_sign_up_name.text.toString(), this))
        return (ti_sign_up_name.error != null)
    }

    private fun checkEmail(): Boolean{
        setError(ti_sign_up_email, FieldsValidatorUtil.isEmpty(et_sign_up_email.text.toString(), this))
        return (ti_sign_up_email.error != null)
    }

    private fun checkPass(): Boolean{
        setError(ti_sign_up_pass, FieldsValidatorUtil.isEmpty(et_sign_up_pass.text.toString(), this))
        return (ti_sign_up_pass.error != null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            RESULT_GALARY -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.data?.let {
                        val contentURI = it
//                        makeCopy(contentURI)
                        setImage(contentURI)
                    }
                }
            }

            RESULT_CAMERA -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.extras?.let {
                        val bitmap = it.get("data") as Bitmap
                        val uri = getBitmapUri(bitmap)
                        setImage(uri)
//                        startCropperActivity(uri)
                    }

                }
            }

//            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
//                data?.let {
//                    val result: CropImage.ActivityResult = CropImage.getActivityResult(data);
//                    if (resultCode == Activity.RESULT_OK) {
//
//                        val resultUri = result.getUri();
//                        makeCopy(resultUri)
//
//                    } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                        Timber.i("Crop image error = ${result.error}")
//                    }else{}
//                }
//            }

        }
    }
    private fun getBitmapUri(bitmap: Bitmap): Uri {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ByteArrayOutputStream());
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "itemPhoto", null);
        return  Uri.parse(path)
    }

private fun setImage(uri: Uri){

//    civ_sign_up_avatar.setImageURI(uri)
//    iv_sign_up_avatar.visibility = View.GONE
}




    private fun cameraPressed(){
        val arrayList: Array<String> = arrayOf("Camera","Galary")

        AlertDialog.Builder(this)
            .setItems(arrayList, { dialog, which ->

                when (which) {
                    0 -> {
                        if(Injector.permissionManager.isPermissionCameraWriteStorageGranted(this))
                            openCamera()
                    }
                    1 -> {
                        if(Injector.permissionManager.isPermissionForGalleryGranted(this))
                            openPhotoLibrary()
                    }
                }
            })
            .setNegativeButton(resources.getString(R.string.cancel), { dialog, _ -> dialog.dismiss() })
            .show()
    }


//    private fun startCropperActivity(uri: Uri) {
//        CropImage.activity(uri)
//            .setGuidelines(CropImageView.Guidelines.ON)
//            .setAspectRatio(1, 1)
//            .start(this)
//    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionManager.PERMISSION_CAMERA,
            PermissionManager.PERMISSION_STORAGE_FOR_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                   // MessageUtil.showToast("Permission for camera is not allowed")
                }
            }
            PermissionManager.PERMISSION_STORAGE_FOR_GALLERY -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openPhotoLibrary()
                } else {
                    //MessageUtil.showToast(getString(R.string.perrmission_for_galary))
                }
            }
        }
    }

    private fun openPhotoLibrary() {
        if(!Injector.permissionManager.isPermissionForGalleryGranted(this)) return

        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, RESULT_GALARY)
            }
        }
    }

    private fun openCamera() {
        if(!Injector.permissionManager.isPermissionCameraWriteStorageGranted(this)) return

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, RESULT_CAMERA)
            }
        }
    }
}
