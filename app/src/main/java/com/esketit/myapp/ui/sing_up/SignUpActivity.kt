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
        Injector.auth.createUserWithEmailAndPassword(et_email.text.toString(), et_pass.text.toString())
            .addOnCompleteListener { task: Task<AuthResult> ->
                if(task.isSuccessful){
                    Log.i("Sign Up", "Create user success")
                    Log.i("Sign Up", Injector.auth.currentUser?.uid)
                    addUserInDB()
                }else{
                    Log.i("Sign Up", "Create user failure")
                    Log.i("Sign Up", task.exception?.message)
                }
            }
    }

    private fun addUserInDB(){

    }


}
