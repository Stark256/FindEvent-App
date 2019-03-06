package com.esketit.myapp.ui.sing_up

import android.arch.lifecycle.ViewModel
import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse
import java.io.ByteArrayOutputStream

class SignUpViewModel: ViewModel() {

    fun signUpPressed(email: String, pass: String, name: String, uri: Uri?, response: (FirebaseResponse) -> Unit){
        Injector.emailAuth.signUp(email, pass, name, uri) { firebaseResponse -> response(firebaseResponse) }
    }

    fun getBitmapUri(bitmap: Bitmap, contentResolver:ContentResolver): Uri {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ByteArrayOutputStream());
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "itemPhoto", null);
        return  Uri.parse(path)
    }

}
