package com.esketit.myapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, WelcomeActivity::class.java))

//        Injector.themesManager.customizeToolbar(this, toolbar)
//        Injector.themesManager.customizeButton(this, btn1)
//        Injector.themesManager.customizeTextInputEditText(this, tiet)
//        Injector.themesManager.customizeTextInputLayout(this, til)
    }
}
