package com.skiddie.dadsbakery.ui.profile.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_profile_account.*


class ProfileAccountFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback {

    private var menuArrayList : ArrayList<com.skiddie.dadsbakery.model.dummy.ProfileMenuModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        var adapter = ProfileMenuAdapter(menuArrayList, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

    fun initDataDummy(){
        menuArrayList = ArrayList()
        menuArrayList.add(com.skiddie.dadsbakery.model.dummy.ProfileMenuModel("Edit Profile"))
        menuArrayList.add(com.skiddie.dadsbakery.model.dummy.ProfileMenuModel("Home Address"))
        menuArrayList.add(com.skiddie.dadsbakery.model.dummy.ProfileMenuModel("Security"))
        menuArrayList.add(com.skiddie.dadsbakery.model.dummy.ProfileMenuModel("Payments"))
    }

    override fun onClick(v: View, data: com.skiddie.dadsbakery.model.dummy.ProfileMenuModel) {
        Toast.makeText(context, "test", Toast.LENGTH_LONG).show()
    }


}