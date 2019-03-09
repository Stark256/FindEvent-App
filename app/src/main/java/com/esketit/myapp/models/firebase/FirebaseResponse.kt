package com.esketit.myapp.models.firebase

import java.lang.Exception

class FirebaseResponse{
    var success = false
    var data: Any? = null
//    var type: Int = 0

    var exception: Exception? = null
    var localizedMessage: String = ""
    var message: String? = null

    constructor(success: Boolean, exception: Exception?){
        this.success = success
        exception?.let { setExceptionMessages(it) }
    }

    constructor(success: Boolean, data: Any?, exception: Exception?){
        this.success = success
        this.data = data
        exception?.let { setExceptionMessages(it) }
    }


    private fun setExceptionMessages(exception: Exception){
        this.exception = exception
        this.localizedMessage = exception.localizedMessage
        this.message = exception.message
    }

//    enum class DataType(val value: Int){
//        TYPE_USER(1)
//    }

/*

ERROR_UNKNOWN	An unknown error occurred.
ERROR_OBJECT_NOT_FOUND	No object exists at the desired reference.
ERROR_BUCKET_NOT_FOUND	No bucket is configured for Cloud Storage
ERROR_PROJECT_NOT_FOUND	No project is configured for Cloud Storage
ERROR_QUOTA_EXCEEDED	Quota on your Cloud Storage bucket has been exceeded. If you're on the free tier, upgrade to a paid plan. If you're on a paid plan, reach out to Firebase support.
ERROR_NOT_AUTHENTICATED	User is unauthenticated, please authenticate and try again.
ERROR_NOT_AUTHORIZED	User is not authorized to perform the desired action, check your rules to ensure they are correct.
ERROR_RETRY_LIMIT_EXCEEDED	The maximum time limit on an operation (upload, download, delete, etc.) has been excceded. Try again.
ERROR_INVALID_CHECKSUM	File on the client does not match the checksum of the file received by the server. Try uploading again.
ERROR_CANCELED	User canceled the operation.

 */
}
