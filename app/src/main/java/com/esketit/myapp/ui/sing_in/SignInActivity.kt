package com.esketit.myapp.ui.sing_in

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.BaseActivity
import com.esketit.myapp.util.FieldsValidatorUtil
import kotlinx.android.synthetic.main.activity_sing_in.*

class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        btn_sign_in.setOnClickListener { btnPressed() }

    }

    private fun btnPressed(){
        if(fieldValidation()) {
            Injector.emailAuth.signIn(et_sign_in_email.text.toString(), et_sign_in_pass.text.toString(), { response ->
                if (response.success) {
                    setResult(Activity.RESULT_OK, Intent())
                    finish()
                } else {
                    showError(response.localizedMessage)
                }
            })
        }
    }

    private fun fieldValidation(): Boolean{
        var isValid = true

        if(checkEmail()) isValid = false
        if(checkPass()) isValid = false

        return  isValid
    }


    private fun checkEmail(): Boolean{
        setError(ti_sign_in_email, FieldsValidatorUtil.isEmpty(et_sign_in_email.text.toString(), this))
        return (ti_sign_in_email.error != null)
    }

    private fun checkPass(): Boolean{
        setError(ti_sign_in_pass, FieldsValidatorUtil.isEmpty(et_sign_in_pass.text.toString(), this))
        return (ti_sign_in_pass.error != null)
    }

}
