package com.esketit.myapp.ui.main.events

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class EventsFragment: BaseFragment(){

    private lateinit var viewModel: EventsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle(toolbar_view_events.toolbar, contextMain.getString(R.string.title_events))

        initViewModel()
    }

    private fun initViewModel(){
        this.viewModel = ViewModelProviders.of(this).get(EventsViewModel::class.java)
    }

}
