package com.esketit.myapp.ui.sing_up

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        btn_sing_up.setOnClickListener { btnPressed() }

        customizeView()
    }

    private fun customizeView(){
        Injector.themesManager.customizeButton(this, btn_sing_up)
    }

    private fun btnPressed(){
        Injector.emailAuth.signUp("testUser1@gmail.com", "testUser1", "Test User Name 1", {response ->
            if(response.success){
                setResult(Activity.RESULT_OK, Intent())
                finish()
            }else{ showError(response.localizedMessage)}
        })
    }

}
