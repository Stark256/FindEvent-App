package com.esketit.myapp.ui.bug_report

import android.os.Bundle
import android.view.MenuItem
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_bug_report.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class BugReportActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug_report)

        setSupportActionBar(this.toolbar_view_bug_report.toolbar, true, true)
        setToolbarTitle(getString(R.string.bug_report))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@BugReportActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }
}
