package com.esketit.myapp.ui.sing_in

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_sing_in.*

class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        btn_sign_in.setOnClickListener { btnPressed() }

        customizeView()
    }

    private fun customizeView(){
        Injector.themesManager.customizeButton(this, btn_sign_in)
    }

    private fun btnPressed(){
        Injector.emailAuth.signIn("testUser1@gmail.com", "testUser1", {response ->
            if(response.success){
                setResult(Activity.RESULT_OK, Intent())
                finish()
            }else{ showError(response.localizedMessage)}
        })
    }

}
