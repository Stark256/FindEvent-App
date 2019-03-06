package com.esketit.myapp.ui.main.games

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class GamesFragment: BaseFragment(){

    private lateinit var viewModel: GamesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle(toolbar_view_games.toolbar, contextMain.getString(R.string.title_games))

        initViewModel()
    }

    private fun initViewModel(){
        this.viewModel = ViewModelProviders.of(this).get(GamesViewModel::class.java)
    }

}
