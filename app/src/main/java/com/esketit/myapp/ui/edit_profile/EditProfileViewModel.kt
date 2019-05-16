package com.esketit.myapp.ui.edit_profile

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.ContentResolver
import android.graphics.Bitmap
import android.location.Location
import android.net.Uri
import android.provider.MediaStore
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.google.firebase.firestore.GeoPoint
import java.io.ByteArrayOutputStream

class EditProfileViewModel: ViewModel(){

    var user = MutableLiveData<User>()
    var uri: Uri? = null
    var location: Location? = null

    var isLocationEnabled: Boolean
        get() { return Injector.storageManager.getLocationEnabled() }
        set(value) { Injector.storageManager.setLocationEnabled(value) }

    fun updateUI() {
        user.value = Injector.userManager.activeUser
    }


    fun updateUser(username: String, description: String? = null,  response: (FirebaseResponse) -> Unit) {
        val user = Injector.userManager.activeUser
        user?.let {
            user.name = username
            description?.let { user.description = it }
            user.cordinate = if(location != null) GeoPoint(location!!.latitude, location!!.longitude) else null

            if(uri != null) {
                Injector.services.storageService.uploadAvatarImage(
                    uri!!, user.id) { imageResponse, url ->
                    if(imageResponse.success) {
                        url?.let { user.avatarImgURL = it }
                        Injector.services.userRepository.updateUser(user) { firebaseResponse ->
                            if (firebaseResponse.success) {
                                Injector.userManager.updateActiveUser { userResponse ->
                                    response(userResponse)
                                }
                            } else { response(firebaseResponse) }
                        }
                    } else { response(imageResponse) }
                }

            } else {
                Injector.services.userRepository.updateUser(user) { firebaseResponse ->
                    if(firebaseResponse.success) {
                        Injector.userManager.updateActiveUser { userResponse ->
                            response(userResponse)
                        }
                    } else { response(firebaseResponse) }
                }
            }


        }

    }

    fun getBitmapUri(bitmap: Bitmap, contentResolver: ContentResolver): Uri {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ByteArrayOutputStream());
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "itemPhoto", null);
        return  Uri.parse(path)
    }
}
