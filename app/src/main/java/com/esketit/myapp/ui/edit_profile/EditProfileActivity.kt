package com.esketit.myapp.ui.edit_profile

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.esketit.myapp.R
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.ui.base.BaseActivity
import com.esketit.myapp.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*




class  EditProfileActivity : BaseActivity(), EditProfileAdapter.EditProfileClickListener{

    private lateinit var viewModel: EditProfileViewModel
    private lateinit var adapter: EditProfileAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        setSupportActionBar(this.toolbar_view_edit_profile.toolbar, true, true)
        setToolbarTitle(getString(R.string.edit_profile))
        this.toolbar_view_edit_profile.toolbar.inflateMenu(R.menu.menu_edit_profile)
        this.toolbar_view_edit_profile.toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.mi_done){
                // TODO done pressed
            }
            false
        }

        initView()

        initViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@EditProfileActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initViewModel(){
        this.viewModel = ViewModelProviders.of(this).get(EditProfileViewModel::class.java)
    }

    private fun initView(){
        this.adapter = EditProfileAdapter(this, this)
        rv_edit_profile.layoutManager = LinearLayoutManager(this)
        rv_edit_profile.adapter = this.adapter
    }


    override fun logOutPressed() {
        Injector.auth.signOut()
        val intentWelcome = Intent(this, WelcomeActivity::class.java)
        intentWelcome.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intentWelcome)
        finish()
    }

    override fun locationPressed() {}
}
