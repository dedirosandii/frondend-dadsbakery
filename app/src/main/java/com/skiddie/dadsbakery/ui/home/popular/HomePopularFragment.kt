package com.skiddie.dadsbakery.ui.home.popular

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.ui.detail.DetailActivity
import com.skiddie.dadsbakery.ui.home.newtaste.HomeNewtasteAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomePopularFragment : Fragment(), HomeNewtasteAdapter.ItemAdapterCallback {

    private var popularList : ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_popular, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        popularList = arguments?.getParcelableArrayList("data")

//        initDataDummy()
        val adapter = popularList?.let { HomeNewtasteAdapter(it, this) }
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

//    fun initDataDummy(){
//        foodList = ArrayList()
//        foodList.add(HomeVerticalModel("Pisang Goreng", "10000","",5f))
//        foodList.add(HomeVerticalModel("Pisang manis", "50000","",4f))
//        foodList.add(HomeVerticalModel("Goreng", "4000","",4.5f))
//    }

    override fun onClick(v: View, data: com.skiddie.dadsbakery.model.reponse.home.Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }

}