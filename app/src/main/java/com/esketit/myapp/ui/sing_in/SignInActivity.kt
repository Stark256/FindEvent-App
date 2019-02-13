package com.esketit.myapp.ui.sing_in

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.android.synthetic.main.activity_sing_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        btn_sign_in.setOnClickListener { btnPressed() }
    }

    private fun btnPressed(){
//        Injector.auth.signInWithEmailAndPassword(et_email.text.toString(), et_pass.text.toString())
//            .addOnCompleteListener { task: Task<AuthResult> ->
//                if(task.isSuccessful){
//                    Log.i("Sing In", "Sing in success")
//
//                }else{
//                    Log.i("Sing In", "Sing in failure")
//                    Log.i("Sing In", task.exception?.message)
//                }
//            }
    }

}
