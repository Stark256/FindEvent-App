package com.esketit.myapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.esketit.myapp.ui.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, WelcomeActivity::class.java))
    }
}
