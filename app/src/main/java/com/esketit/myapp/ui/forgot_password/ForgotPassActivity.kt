package com.esketit.myapp.ui.forgot_password

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseActivity
import com.esketit.myapp.util.FieldsValidatorUtil
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class ForgotPassActivity : BaseActivity() {

    private lateinit var viewModel: ForgotPassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        setSupportActionBar(this.toolbar_view_forgot_pass.toolbar, true, false)

        initViewModel()

        initView()
    }

    private fun initViewModel(){
        this.viewModel = ViewModelProviders.of(this).get(ForgotPassViewModel::class.java)
    }

    private fun initView(){
        btn_reset_pass.setOnClickListener { resetPressed() }

        etv_reset_email.initBuilder(hintRes = R.string.name,
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
            imeOption = EditorInfo.IME_ACTION_DONE)
        etv_reset_email?.onFocusChanged { hasFocus -> if(!hasFocus) checkEmail() }
        etv_reset_email?.onActionPressed { resetPressed() }
    }

    /*     Button Actions     */

    private fun resetPressed(){
        if(fieldValidation()){
            viewModel.resetPassword(etv_reset_email.text.toString()) { response ->
                hideProgressDialog()
                if (response.success) { /*finish()*/ }
                else { showError(response.localizedMessage) }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@ForgotPassActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fieldValidation(): Boolean{
        var isValid = true

        if(checkEmail()) isValid = false

        return  isValid
    }


    private fun checkEmail(): Boolean{
        etv_reset_email.setError(FieldsValidatorUtil.isEmailValid(etv_reset_email.text.toString(), this))
        return etv_reset_email.hasError
    }
}
