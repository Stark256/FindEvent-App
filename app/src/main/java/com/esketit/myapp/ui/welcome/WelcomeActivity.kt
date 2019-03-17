package com.esketit.myapp.ui.welcome

import  android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.esketit.myapp.ui.main.MainActivity
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.base.BaseActivity
import com.esketit.myapp.ui.sing_in.SignInActivity
import com.esketit.myapp.ui.sing_up.SignUpActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity() {

    private val RESULT_SIGN_UP = 25
    private val RESULT_SIGN_IN = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        if(Injector.auth.isUserLogged){
            showProgressDialog()
            Injector.userManager.updateActiveUser{ updateResponse ->
                hideProgressDialog()
                    if(updateResponse.success){
                        startMainActivityAndFinish() }
                    else{ updateResponse.exception?.let { showError(it.localizedMessage) } }
            }
        }else{ showButtons() }


        btn_sign_up.setOnClickListener { startActivityForResult(Intent(this, SignUpActivity::class.java), RESULT_SIGN_UP) }
        btn_sign_in.setOnClickListener { startActivityForResult(Intent(this, SignInActivity::class.java), RESULT_SIGN_IN) }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            RESULT_SIGN_IN -> {
                if(resultCode == Activity.RESULT_OK)
                    startMainActivityAndFinish()
            }
            RESULT_SIGN_UP -> {
                if(resultCode == Activity.RESULT_OK)
                    startMainActivityAndFinish()
            }
        }
    }

    private fun showButtons(){
        btn_sign_up.animate()
            .setDuration(250)
            .alpha(1f)
            .withEndAction { btn_sign_up.isClickable = true }
            .start()

        btn_sign_in.animate()
            .setDuration(250)
            .alpha(1f)
            .withEndAction { btn_sign_in.isClickable = true }
            .start()
    }

    private fun startMainActivityAndFinish(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
