package com.esketit.myapp.ui

import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.esketit.myapp.R

abstract class BaseActivity: AppCompatActivity(){

//    val errorDialog = AlertDialog.Builder(this)
//        .create()


    fun showError(message: String){
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), { dialog, which ->  dialog.dismiss() })
            .show()
    }

    fun setError(layout: TextInputLayout, error: String?) {
        layout.isErrorEnabled = error != null
        layout.error = error
    }

}
