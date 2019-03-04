package com.esketit.myapp.ui.sing_in

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.esketit.myapp.App
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.BaseActivity
import com.esketit.myapp.util.FieldsValidatorUtil
import kotlinx.android.synthetic.main.activity_sing_in.*

class SignInActivity : BaseActivity() {

    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        setSupportActionBar(this.toolbar_sign_in, true, false)

        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)

        initView()
    }

    private fun initView(){
        btn_sign_in.setOnClickListener { btnPressed() }
    }

    private fun btnPressed(){
        if(fieldValidation()) {
            showProgressDialog()
            viewModel.signInPressed(et_sign_in_email.text.toString(), et_sign_in_pass.text.toString(), { response ->
                if (response.success) {
                    hideProgressDialog()
                    setResult(Activity.RESULT_OK, Intent())
                    finish()
                } else {
                    showError(response.localizedMessage)
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@SignInActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
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
