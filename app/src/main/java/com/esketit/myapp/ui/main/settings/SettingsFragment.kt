package com.esketit.myapp.ui.main.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.models.local.settings_models.SettingsBaseItem
import com.esketit.myapp.models.local.settings_models.SettingsEmptyItem
import com.esketit.myapp.models.local.settings_models.SettingsProfileItem
import com.esketit.myapp.models.local.settings_models.SettingsSettingItem
import com.esketit.myapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class SettingsFragment: BaseFragment(), SettingsAdapter.SettingsClickListener{

    private lateinit var viewModel: SettingsViewModel
    private val adapter: SettingsAdapter = SettingsAdapter(this)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle(toolbar_view_settings.toolbar, contextMain.getString(R.string.title_settings))

        initViewModel()


//        btn1.setOnClickListener {
//            Injector.auth.signOut()
////            startActivity(Intent(this, WelcomeActivity::class.java))
////            finish()
//        }

        initView()
    }


    private fun initViewModel(){
        this.viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        viewModel.apply {
             user.observe(this@SettingsFragment, Observer<User?> {
                 updateAdapterItems(it)
             })

            updateApterItems()
        }
//        viewModel.apply {
//            exception.observe(this@SettingsFragment, Observer<FirebaseResponse>{
//                it?.localizedMessage?.let { contextMain.showError(it) }
//            })
//
//            settingsItems.observe(this@SettingsFragment, Observer<ArrayList<SettingsBaseItem>>{
//                it?.let {
//                    adapter.replaceAll(it)
//                }
//            })
//
////            loadCurrentUser()
//        }
    }

    private fun initView(){
        rv_settings.layoutManager = LinearLayoutManager(contextMain)
        rv_settings.adapter = this.adapter
    }

    override fun onItemPressed(settingsItemType: Int) {

    }

    private fun updateAdapterItems(user: User?){
        adapter.replaceAll(arrayListOf(
                SettingsProfileItem(user),
                SettingsEmptyItem(),
                SettingsSettingItem(R.drawable.notification, R.string.notification),
                SettingsSettingItem(R.drawable.appearance, R.string.appearance),
                SettingsSettingItem(R.drawable.language, R.string.language),
                SettingsEmptyItem(),
                SettingsSettingItem(R.drawable.bug, R.string.bug_report),
                SettingsSettingItem(R.drawable.aboutus, R.string.about_us)
            ))
    }
}
