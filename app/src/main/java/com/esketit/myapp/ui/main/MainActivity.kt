package com.esketit.myapp.ui.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.managers.LocationHelper
import com.esketit.myapp.ui.base.BaseActivity
import com.esketit.myapp.ui.main.chats.ChatsFragment
import com.esketit.myapp.ui.main.events.EventsFragment
import com.esketit.myapp.ui.main.friends.FriendsFragment
import com.esketit.myapp.ui.main.games.GamesFragment
import com.esketit.myapp.ui.main.settings.SettingsFragment
import com.esketit.myapp.util.PermissionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {



    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.mi_events -> { replaceFragment(EventsFragment()) }
            R.id.mi_friends -> { replaceFragment(FriendsFragment()) }
            R.id.mi_chats -> { replaceFragment(ChatsFragment()) }
            R.id.mi_games -> { replaceFragment(GamesFragment()) }
            R.id.mi_settings -> { replaceFragment(SettingsFragment()) }
        }

        return@OnNavigationItemSelectedListener true
    }


    private lateinit var viewModel: MainViewModel
    private val locationManager = LocationHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initView()

        replaceFragment(EventsFragment())

        viewModel.setTimer()
        checkLocationEnabled()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            PermissionManager.RESULT_LOCATION -> {
                checkLocationEnabled()
            }
        }
    }

    private fun checkLocationEnabled(){
        if(viewModel.currentLocationEnabled) {
            if(Injector.permissionManager.isPermissionLocationGranted(this)) {
                if (!isLocationEnabled()) {
                    locationManager.init(this)
                } else {
                    viewModel.updateLocation(Injector.locationManager.getLastLocation(this))
                }
            }
        }
    }

    private fun isLocationEnabled(): Boolean{
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionManager.PERMISSION_ACCESS_FINE_LOCATION -> {
                checkLocationEnabled()
            }
        }
    }

    private fun initView(){

        this.bottomNavView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }

//    fun popBack() {
//        supportFragmentManager.popBackStackImmediate();
//    }

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
