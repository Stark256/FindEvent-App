package com.esketit.myapp.ui.edit_profile

import android.os.Bundle
import android.view.MenuItem
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class EditProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        setSupportActionBar(this.toolbar_view_edit_profile.toolbar, true, true)
        setToolbarTitle(getString(R.string.edit_profile))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@EditProfileActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }
}
