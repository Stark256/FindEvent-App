package com.esketit.myapp.ui.notification

import android.os.Bundle
import android.view.MenuItem
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class NotificationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setSupportActionBar(this.toolbar_view_notification.toolbar, true, true)
        setToolbarTitle(getString(R.string.notification))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@NotificationActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }
}
