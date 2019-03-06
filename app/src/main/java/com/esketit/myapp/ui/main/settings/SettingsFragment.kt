package com.esketit.myapp.ui.main.settings

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class SettingsFragment: BaseFragment(){

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle(toolbar_view_settings.toolbar, contextMain.getString(R.string.title_settings))

        initViewModel()


        btn1.setOnClickListener {
            Injector.auth.signOut()
//            startActivity(Intent(this, WelcomeActivity::class.java))
//            finish()
        }
    }

    private fun initViewModel(){
        this.viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
    }

}
