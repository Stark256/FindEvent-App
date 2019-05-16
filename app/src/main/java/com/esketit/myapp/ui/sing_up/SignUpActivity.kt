package com.esketit.myapp.ui.sing_up

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.managers.LocationHelper
import com.esketit.myapp.ui.base.BaseActivity
import com.esketit.myapp.util.FieldsValidatorUtil
import com.esketit.myapp.util.PermissionManager
import com.esketit.myapp.view.edit_image_view.EditImageDialogBaseClickListener
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import android.content.Context
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.inputmethod.EditorInfo

class SignUpActivity : BaseActivity() {

    private val RESULT_CAMERA = 15
    private val RESULT_GALARY = 25

    private var uri: Uri? = null

    private lateinit var viewModel: SignUpViewModel

    private val locationManager = LocationHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setSupportActionBar(this.toolbar_view_sign_up.toolbar, true, false)

        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)

        initView()

        checkLocationEnabled()
    }



    private fun initView(){
        btn_sing_up.setOnClickListener { signUpPressed() }
        eiv_sign_up_avatar.setBigView()
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

        etv_sign_up_name.initBuilder(hintRes = R.string.name,
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_CLASS_TEXT,
            imeOption = EditorInfo.IME_ACTION_NEXT)
        etv_sign_up_name?.onFocusChanged { hasFocus -> if(!hasFocus) checkUserName() }
        etv_sign_up_name?.onActionPressed { checkUserName() }

        etv_sign_up_email.initBuilder(hintRes = R.string.email,
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
            imeOption = EditorInfo.IME_ACTION_NEXT)
        etv_sign_up_email?.onFocusChanged { hasFocus -> if(!hasFocus) checkEmail() }
        etv_sign_up_email?.onActionPressed { checkEmail() }

        etv_sign_up_password.initBuilder(hintRes = R.string.pass,
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD,
            imeOption = EditorInfo.IME_ACTION_DONE)
        etv_sign_up_password?.onFocusChanged { hasFocus -> if(!hasFocus) checkPass() }
        etv_sign_up_password?.onActionPressed { signUpPressed() }
    }


    private fun signUpPressed(){
        if(isFieldsValid()) {
            showProgressDialog()
            viewModel.signUpPressed(
                etv_sign_up_email.text.toString(),
                etv_sign_up_password.text.toString(),
                etv_sign_up_name.text.toString(), uri) { response ->
                hideProgressDialog()
                if (response.success) {
                    setResult(Activity.RESULT_OK, Intent())
                    finish()
                } else { showError(response.localizedMessage) }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@SignUpActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
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
                        val uri = viewModel.getBitmapUri(bitmap, contentResolver)
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

            PermissionManager.RESULT_LOCATION -> {
                checkLocationEnabled()
            }

        }
    }
    private fun setImage(uri: Uri){
        this.uri = uri
        eiv_sign_up_avatar.loadImage(uri)
    }

    private fun startCropperActivity(uri: Uri) {
        CropImage.activity(uri)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1, 1)
            .start(this)
    }

    private fun checkLocationEnabled(){
        if(Injector.permissionManager.isPermissionLocationGranted(this)) {
            if (!isLocationEnabled()) {
                locationManager.init(this)
            } else {
               viewModel.setLocation(Injector.locationManager.getLastLocation(this))
            }
        }
    }

    private fun isLocationEnabled(): Boolean{
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
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
            PermissionManager.PERMISSION_ACCESS_FINE_LOCATION -> {
                checkLocationEnabled()
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


    private fun isFieldsValid(): Boolean{
        var isValid = true

        if(checkUserName()) isValid = false
        if(checkEmail()) isValid = false
        if(checkPass()) isValid = false

        return  isValid
    }

    private fun checkUserName(): Boolean{
        etv_sign_up_name.setError(FieldsValidatorUtil.isNameValid(etv_sign_up_name.text.toString(), this))
        return etv_sign_up_name.hasError
    }

    private fun checkEmail(): Boolean{
        etv_sign_up_email.setError(FieldsValidatorUtil.isEmailValid(etv_sign_up_email.text.toString(), this))
        return etv_sign_up_email.hasError
    }

    private fun checkPass(): Boolean{
        etv_sign_up_password.setError(FieldsValidatorUtil.isPassValid(etv_sign_up_password.text.toString(), this))
        return etv_sign_up_password.hasError
    }

}
