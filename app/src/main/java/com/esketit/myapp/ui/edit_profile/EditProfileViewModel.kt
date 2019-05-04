package com.esketit.myapp.ui.edit_profile

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.User
import java.io.ByteArrayOutputStream

class EditProfileViewModel: ViewModel(){

    var user = MutableLiveData<User>()


    fun updateUI() {
        user.value = Injector.userManager.activeUser
    }




    fun getBitmapUri(bitmap: Bitmap, contentResolver: ContentResolver): Uri {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ByteArrayOutputStream());
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "itemPhoto", null);
        return  Uri.parse(path)
    }
}
