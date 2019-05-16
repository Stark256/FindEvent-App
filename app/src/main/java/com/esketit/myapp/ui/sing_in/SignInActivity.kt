package com.esketit.myapp.ui.sing_in

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseActivity
import com.esketit.myapp.ui.forgot_password.ForgotPassActivity
import com.esketit.myapp.util.FieldsValidatorUtil
import kotlinx.android.synthetic.main.activity_sing_in.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class SignInActivity : BaseActivity() {

    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        setSupportActionBar(this.toolbar_view_sign_in.toolbar, true, false)

        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)

        initView()
    }

    private fun initView(){
        btn_sign_in.setOnClickListener { signInPressed() }
        bt_forgot_pass.setOnClickListener { forgotPassPressed() }

        etv_sign_in_email.initBuilder(hintRes = R.string.email,
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
            imeOption = EditorInfo.IME_ACTION_NEXT)
        etv_sign_in_email?.onFocusChanged { hasFocus -> if(!hasFocus) checkEmail() }
        etv_sign_in_email?.onActionPressed { checkEmail() }

        etv_sign_in_password.initBuilder(hintRes = R.string.pass,
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD,
            imeOption = EditorInfo.IME_ACTION_DONE)
        etv_sign_in_password?.onFocusChanged { hasFocus -> if(!hasFocus) checkPass() }
        etv_sign_in_password?.onActionPressed { signInPressed() }
    }

    /*     Button Actions     */

    private fun signInPressed(){
        if(isFieldsValid()) {
            showProgressDialog()
            viewModel.signInPressed(etv_sign_in_email.text.toString(), etv_sign_in_password.text.toString()) { response ->
                hideProgressDialog()
                if (response.success) {
                    setResult(Activity.RESULT_OK, Intent())
                    finish()
                } else { showError(response.localizedMessage) }
            }
        }
    }

    private fun forgotPassPressed(){
        startActivity(Intent(this, ForgotPassActivity::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@SignInActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isFieldsValid(): Boolean{
        var isValid = true

        if(checkEmail()) isValid = false
        if(checkPass()) isValid = false

        return  isValid
    }


    private fun checkEmail(): Boolean{
        etv_sign_in_email.setError(FieldsValidatorUtil.isEmailValid(etv_sign_in_email.text.toString(), this))
        return etv_sign_in_email.hasError
    }

    private fun checkPass(): Boolean{
        etv_sign_in_password.setError(FieldsValidatorUtil.isPassValid(etv_sign_in_password.text.toString(), this))
        return etv_sign_in_password.hasError
    }

}
