package com.esketit.myapp.ui.about_us

import android.os.Bundle
import android.view.MenuItem
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class AboutUsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        setSupportActionBar(this.toolbar_view_aboutus.toolbar, true, true)
        setToolbarTitle(getString(R.string.about_us))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@AboutUsActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }
}
