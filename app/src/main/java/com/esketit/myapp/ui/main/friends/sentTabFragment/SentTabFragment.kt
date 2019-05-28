package com.esketit.myapp.ui.main.friends.sentTabFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esketit.myapp.R
import com.esketit.myapp.ui.base.BaseFragment
import com.esketit.myapp.ui.main.friends.FriendsListener

class SentTabFragment: BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(listener: FriendsListener.OnSentDataTransfer) : SentTabFragment {
            val fragment = SentTabFragment()
            fragment.listener = listener
            return fragment
        }

    }

    private lateinit var listener: FriendsListener.OnSentDataTransfer


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab_fragment_sent, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listener.onSentNeedRefresh {  arrayList ->
            arrayList
        }
    }
}
