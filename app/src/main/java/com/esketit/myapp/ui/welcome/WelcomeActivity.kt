package com.esketit.myapp.ui.welcome

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.esketit.myapp.R
import com.esketit.myapp.ui.sing_in.SignInActivity
import com.esketit.myapp.ui.sing_up.SignUpActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        btn_sign_up.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }
        btn_sign_in.setOnClickListener { startActivity(Intent(this, SignInActivity::class.java)) }

    }
}
