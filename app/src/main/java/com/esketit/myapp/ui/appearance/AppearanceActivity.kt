package com.esketit.myapp.ui.appearance

import android.os.Bundle
import android.view.MenuItem
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_appearance.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class AppearanceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appearance)

        setSupportActionBar(this.toolbar_view_appearance.toolbar, true, true)
        setToolbarTitle(getString(R.string.appearance))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@AppearanceActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }
}
