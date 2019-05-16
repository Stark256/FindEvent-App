package com.esketit.myapp.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import com.esketit.myapp.ui.main.MainActivity

open class BaseFragment(): Fragment(){

    var isFragmentCreated  = false

    val contextMain: MainActivity
        get(){ return activity as MainActivity
        }

    fun setToolbarTitle(toolbar: Toolbar, title: String){
        toolbar.title = title
    }

    fun showProgressDialog(){ (activity as MainActivity).showProgressDialog() }
    fun hideProgressDialog(){ (activity as MainActivity).hideProgressDialog() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFragmentCreated = true
    }

    override fun onDestroy() {
        super.onDestroy()
        isFragmentCreated = false
    }
}
