package com.esketit.myapp.ui.main.friends

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class FriendsFragment: BaseFragment(){

    private lateinit var viewModel: FriendsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle(toolbar_view_friends.toolbar, contextMain.getString(R.string.title_friends))

        initViewModel()
    }

    private fun initViewModel(){
        this.viewModel = ViewModelProviders.of(this).get(FriendsViewModel::class.java)
    }

}
