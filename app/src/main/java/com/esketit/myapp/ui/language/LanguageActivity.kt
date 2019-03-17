package com.esketit.myapp.ui.language

import android.os.Bundle
import android.view.MenuItem
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_language.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class LanguageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        setSupportActionBar(this.toolbar_view_language.toolbar, true, true)
        setToolbarTitle(getString(R.string.language))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@LanguageActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }
}
