package com.esketit.myapp.ui.sing_up

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        btn_sing_up.setOnClickListener { btnPressed() }
    }

    private fun btnPressed(){
        Injector.emailAuth.signUp("testUser@gmail.com", "testUser", "Test User Name")
    }

    private fun addUserInDB(){

    }


}
