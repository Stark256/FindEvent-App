package com.esketit.myapp.ui.main.friends.friendsTabFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseFragment
import com.esketit.myapp.ui.main.friends.FriendsListener

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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab_fragment_friends, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
