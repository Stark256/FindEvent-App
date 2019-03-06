package com.esketit.myapp.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import com.esketit.myapp.ui.main.MainActivity

open class BaseFragment(): Fragment(){

    val contextMain: Context
        get(){ return activity as MainActivity}

    fun setToolbarTitle(toolbar: Toolbar, title: String){
        toolbar.title = title
    }


    fun showProgressDialog(){ (activity as MainActivity).showProgressDialog() }
    fun hideProgressDialog(){ (activity as MainActivity).hideProgressDialog() }
}
