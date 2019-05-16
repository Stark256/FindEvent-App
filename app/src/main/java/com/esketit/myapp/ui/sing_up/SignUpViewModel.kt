package com.esketit.myapp.ui.sing_up

import android.arch.lifecycle.ViewModel
import android.content.ContentResolver
import android.graphics.Bitmap
import android.location.Location
import android.net.Uri
import android.provider.MediaStore
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse
import java.io.ByteArrayOutputStream

class SignUpViewModel: ViewModel() {

    private var currentLocation: Location? = null

    fun signUpPressed(email: String, pass: String, name: String, uri: Uri?, response: (FirebaseResponse) -> Unit){
        Injector.auth.signUp(email, pass, name, uri,
            currentLocation?.latitude,
            currentLocation?.longitude) { firebaseResponse ->

            if(firebaseResponse.success){
                Injector.userManager.updateActiveUser{ updateResponse -> response(updateResponse) }
            }
            else { response(firebaseResponse) }
        }
    }

    fun getBitmapUri(bitmap: Bitmap, contentResolver:ContentResolver): Uri {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ByteArrayOutputStream());
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "itemPhoto", null);
        return  Uri.parse(path)
    }

    fun setLocation(location: Location?){
        this.currentLocation = location
    }

}
