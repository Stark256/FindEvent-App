package com.esketit.myapp.ui.sing_up

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.BaseActivity
import com.esketit.myapp.util.FieldsValidatorUtil
import com.esketit.myapp.util.PermissionManager
import com.esketit.myapp.view.EditImageView.EditImageDialogBaseClickListener
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.ByteArrayOutputStream

class SignUpActivity : BaseActivity() {

    private val RESULT_CAMERA = 15
    private val RESULT_GALARY = 25
    private val RESULT_CROP = 35

    private var uri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        initView()
    }

    private fun initView(){
        btn_sing_up.setOnClickListener { btnPressed() }

        eiv_sign_up_avatar.setAddImageView()
        eiv_sign_up_avatar.setDialogBaseCliclListener(object : EditImageDialogBaseClickListener{
            override fun onGalaryPressed() {
                if(Injector.permissionManager.isPermissionForGalleryGranted(this@SignUpActivity)) {
                    openGallery()
                }
            }

            override fun onCameraPressed() {
                if(Injector.permissionManager.isPermissionCameraWriteStorageGranted(this@SignUpActivity)) {
                    openCamera()
                }
            }
        })
    }


    private fun btnPressed(){
        if(fieldValidation()) {
            Injector.emailAuth.signUp(
                et_sign_up_email.text.toString(),
                et_sign_up_pass.text.toString(),
                et_sign_up_name.text.toString(),
                uri) { response ->
                if (response.success) {
                    setResult(Activity.RESULT_OK, Intent())
                    finish()
                } else {
                    showError(response.localizedMessage)
                }
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            RESULT_GALARY -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.data?.let {
                        val contentURI = it
                        startCropperActivity(contentURI)
                    }
                }
            }

            RESULT_CAMERA -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.extras?.let {
                        val bitmap = it.get("data") as Bitmap
                        val uri = getBitmapUri(bitmap)
                        startCropperActivity(uri)
                    }

                }
            }

            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                data?.let {
                    val result: CropImage.ActivityResult = CropImage.getActivityResult(data);
                    if (resultCode == Activity.RESULT_OK) {

                        val resultUri = result.getUri();
                        setImage(resultUri)

                    } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                        //Timber.i("Crop image error = ${result.error}")
                    }else{}
                }
            }

        }
    }
    private fun setImage(uri: Uri){
        this.uri = uri
        eiv_sign_up_avatar.loadImage(uri)
    }

    private fun getBitmapUri(bitmap: Bitmap): Uri {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ByteArrayOutputStream());
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "itemPhoto", null);
        return  Uri.parse(path)
    }



    private fun startCropperActivity(uri: Uri) {
        CropImage.activity(uri)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1, 1)
            .start(this)
    }


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
                    openGallery()
                } else {
                    //MessageUtil.showToast(getString(R.string.perrmission_for_galary))
                }
            }
        }
    }

    private fun openGallery() {
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
}
