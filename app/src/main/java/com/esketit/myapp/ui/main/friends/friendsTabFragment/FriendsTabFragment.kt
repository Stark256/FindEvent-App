package com.esketit.myapp.ui.main.friends.friendsTabFragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseFragment
import com.esketit.myapp.ui.main.friends.FriendsListener
import kotlinx.android.synthetic.main.tab_fragment_friends.*

class FriendsTabFragment: BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(listener: FriendsListener.OnFriendsDataTransfer) : FriendsTabFragment {
            val fragment = FriendsTabFragment()
            fragment.listener = listener
            return fragment
        }

    }

    private lateinit var listener: FriendsListener.OnFriendsDataTransfer
    private lateinit var adapter: FriendsTabAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab_fragment_friends, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        listener.onFriendsNeedRefresh { arrayList ->
            adapter.replaceAll(arrayList)
        }
    }

    private fun initView() {
        this.adapter = FriendsTabAdapter()

        rv_friends.layoutManager = LinearLayoutManager(contextMain)
        rv_friends.adapter = adapter
    }
}
