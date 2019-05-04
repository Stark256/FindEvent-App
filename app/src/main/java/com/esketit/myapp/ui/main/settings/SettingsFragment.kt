package com.esketit.myapp.ui.main.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.esketit.myapp.R
import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.models.local.settings_models.*
import com.esketit.myapp.ui.about_us.AboutUsActivity
import com.esketit.myapp.ui.appearance.AppearanceActivity
import com.esketit.myapp.ui.base.BaseFragment
import com.esketit.myapp.ui.bug_report.BugReportActivity
import com.esketit.myapp.ui.edit_profile.EditProfileActivity
import com.esketit.myapp.ui.notification.NotificationActivity
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
        toolbar_view_settings.toolbar.inflateMenu(R.menu.menu_settings)
        toolbar_view_settings.toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.mi_edit){ editPressed() }
            false
        }
        initViewModel()

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
    }

    private fun initView(){
        rv_settings.layoutManager = LinearLayoutManager(contextMain)
        rv_settings.adapter = this.adapter
    }

    override fun onItemPressed(settingsItemType: Int) {
        when(settingsItemType){
            SettingsBaseItem.SettingItemType.TYPE_PROFILE.value -> {  editPressed() }
            SettingsBaseItem.SettingItemType.TYPE_NOTIFICATIONS.value -> {  startActivity(Intent(contextMain, NotificationActivity::class.java)) }
            SettingsBaseItem.SettingItemType.TYPE_APPEARANCE.value -> {  startActivity(Intent(contextMain, AppearanceActivity::class.java)) }
            SettingsBaseItem.SettingItemType.TYPE_BUG_REPORT.value -> {  startActivity(Intent(contextMain, BugReportActivity::class.java)) }
            SettingsBaseItem.SettingItemType.TYPE_ABOUT_US.value -> {  startActivity(Intent(contextMain, AboutUsActivity::class.java)) }
        }
    }

    private fun updateAdapterItems(user: User?){
        adapter.replaceAll(arrayListOf(
                SettingsProfileItem(user),
                SettingsEmptyItem(),
                SettingsNotificationItem(R.drawable.notification, R.string.notification),
                SettingsAppearanceItem(R.drawable.appearance, R.string.appearance),
                SettingsEmptyItem(),
                SettingsBugReportItem(R.drawable.bug, R.string.bug_report),
                SettingsAboutUsItem(R.drawable.aboutus, R.string.about_us)
            ))
    }

    private fun editPressed() {
        startActivity(Intent(contextMain, EditProfileActivity::class.java))
    }
}
