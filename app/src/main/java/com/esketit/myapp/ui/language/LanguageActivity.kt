package com.esketit.myapp.ui.language

import android.os.Bundle
import android.view.MenuItem
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_language.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import android.content.Intent
import android.util.DisplayMetrics
import java.util.*


class LanguageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        setSupportActionBar(this.toolbar_view_language.toolbar, true, true)
        setToolbarTitle(getString(R.string.language))

        button.setOnClickListener {
            changeLanguage("ua")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> { this@LanguageActivity.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }

    fun changeLanguage(lang: String){
//        val myLocale = Locale(lang)
        val res = resources
//        val dm = res.displayMetrics
//        val conf = res.configuration
//        conf.setLocale(myLocale)
        res.configuration.setLocale(Locale(lang))
        val refresh = Intent(this, LanguageActivity::class.java)
        startActivity(refresh)
        finish()
    }
}
