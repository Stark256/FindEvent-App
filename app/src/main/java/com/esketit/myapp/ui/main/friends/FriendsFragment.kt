package com.esketit.myapp.ui.main.friends

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.models.local.friends_models.FriendsRequestsModel
import com.esketit.myapp.ui.base.BaseFragment
import com.esketit.myapp.ui.main.friends.friendsTabFragment.FriendsTabFragment
import com.esketit.myapp.ui.main.friends.requestsTabFragment.RequestsTabFragment
import com.esketit.myapp.ui.main.friends.sentTabFragment.SentTabFragment
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class FriendsFragment: BaseFragment(),
    FriendsListener.OnFriendsDataTransfer,
    FriendsListener.OnRequestDataTransfer,
    FriendsListener.OnSentDataTransfer {

    private lateinit var viewModel: FriendsViewModel

    private lateinit var friendsTabTitle: String
    private lateinit var requestsTabTitle: String
    private lateinit var sentTabTitle: String

    private lateinit var friendsTabFragment: FriendsTabFragment
    private lateinit var requestsTabFragment: RequestsTabFragment
    private lateinit var sentTabFragment: SentTabFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() {
//        setToolbarTitle(toolbar_view_friends.toolbar, contextMain.getString(R.string.title_friends))
        friendsTabFragment = FriendsTabFragment.newInstance(this)
        requestsTabFragment = RequestsTabFragment.newInstance(this)
        sentTabFragment = SentTabFragment.newInstance(this)


        this.friendsTabTitle = contextMain.getString(R.string.friends_tab_fragmet_title)
        this.requestsTabTitle = contextMain.getString(R.string.requests_tab_fragmet_title)
        this.sentTabTitle = contextMain.getString(R.string.sent_tab_fragmet_title)

        tab_layout_friends?.addTab(tab_layout_friends.newTab().setIcon(R.drawable.ic_camera))
        tab_layout_friends?.addTab(tab_layout_friends.newTab().setIcon(R.drawable.ic_camera))
        tab_layout_friends?.addTab(tab_layout_friends.newTab().setIcon(R.drawable.ic_camera))

        tab_layout_friends?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) { tabSelected(tab?.position) }
        })


        tab_layout_friends?.getTabAt(0)?.select()
        tabFriendsSelected()
    }

    private fun initViewModel(){
        this.viewModel = ViewModelProviders.of(this).get(FriendsViewModel::class.java)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_friends, fragment)
        transaction.commit()
    }


    private fun tabSelected(position: Int?) {
        when(position) {
            0 -> { tabFriendsSelected() }
            1 -> { tabRequestsSelected() }
            2 -> { tabSentSelected() }
            else -> {}
        }
    }

    private fun tabFriendsSelected() {
        setToolbarTitle(toolbar_view_friends.toolbar, friendsTabTitle)
        replaceFragment(friendsTabFragment)
    }

    private fun tabRequestsSelected() {
        setToolbarTitle(toolbar_view_friends.toolbar, requestsTabTitle)
        replaceFragment(requestsTabFragment)
    }

    private fun tabSentSelected() {
        setToolbarTitle(toolbar_view_friends.toolbar, sentTabTitle)
        replaceFragment(sentTabFragment)
    }

    override fun onFriendsNeedRefresh(arr: (ArrayList<FriendsRequestsModel>) -> Unit) {
        contextMain.showProgressDialog()
        // TODO refresh
        contextMain.hideProgressDialog()
    }

    override fun onRequestNeedRefresh(arr: (ArrayList<FriendsRequestsModel>) -> Unit) {
        contextMain.showProgressDialog()
        // TODO refresh
        contextMain.hideProgressDialog()
    }

    override fun onSentNeedRefresh(arr: (ArrayList<FriendsRequestsModel>) -> Unit) {
        contextMain.showProgressDialog()
        // TODO refresh
        contextMain.hideProgressDialog()
    }
}
