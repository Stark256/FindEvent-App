package com.esketit.myapp.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.mi_events -> {
                //presenter.buyItemPressed()
            }

            R.id.mi_friends -> {
                //presenter.sellItemPressed()
            }

            R.id.mi_chats -> {
                //presenter.categoriesItemPressed()
            }

            R.id.mi_games -> {
               // presenter.chatItemPressed()
            }

            R.id.mi_settings -> {
                //presenter.settingsItemPressed()
            }
        }

        return@OnNavigationItemSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView(){


        this.bottomNavView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        btn1.setOnClickListener {
            Injector.emailAuth.signOut()
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }




    @SuppressLint("RestrictedApi")
    fun BottomNavigationView.disableShiftMode() {
        val menuView = getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShifting(false)
                // set once again checked value, so view will be updated
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e("BottomNav", "Unable to get shift mode field", e)
        } catch (e: IllegalStateException) {
            Log.e("BottomNav", "Unable to change value of shift mode", e)
        }
    }
}
