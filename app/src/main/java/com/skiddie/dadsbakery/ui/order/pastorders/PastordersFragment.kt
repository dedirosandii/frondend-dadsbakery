package com.skiddie.dadsbakery.ui.order.pastorders

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.ui.order.detailsorders.OrdersDetailActivity
import kotlinx.android.synthetic.main.fragment_profile_account.*


class PastordersFragment : Fragment(), PastordersAdapter.ItemAdapterCallback{

    private  var adapter:PastordersAdapter? = null
    private var pastordersList: ArrayList<com.skiddie.dadsbakery.model.reponse.transaction.Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pastorders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pastordersList = arguments?.getParcelableArrayList("data")

        if (!pastordersList.isNullOrEmpty()){
            adapter = PastordersAdapter(pastordersList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            rcList.layoutManager = layoutManager
            rcList.adapter = adapter
        }

    }

    override fun onClick(v: View, data: com.skiddie.dadsbakery.model.reponse.transaction.Data) {
        val detail = Intent(activity, OrdersDetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }


}