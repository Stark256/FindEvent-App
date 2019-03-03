package com.esketit.myapp.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            Injector.emailAuth.signOut()
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }
}
