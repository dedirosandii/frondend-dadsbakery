package com.skiddie.dadsbakery.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.ui.detail.DetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.ivProfile

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View {

    private var newStateList : ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data> = ArrayList()
    private var popularList : ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data> = ArrayList()
    private var recomendedList : ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data> = ArrayList()
    private lateinit var presenter: HomePresenter
    private var progressDialog: Dialog? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = HomePresenter(this)
        presenter.getHome()
//        initDataDummy()


    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        val user = com.skiddie.dadsbakery.FoodMarket.getApp().getUser()
        val userResponse = Gson().fromJson(user, com.skiddie.dadsbakery.model.reponse.login.User::class.java)

        if (userResponse.profilePhotoUrl.isNotEmpty()){
            Glide.with(requireActivity())
                .load(userResponse.profilePhotoUrl)
                    .apply(RequestOptions.circleCropTransform())
                .into(ivProfile)
        }

    }

//    fun initDataDummy(){
//        foodList = ArrayList()
//        foodList.add(HomeModel("Pisang Goreng", "",5f))
//        foodList.add(HomeModel("Pisang manis", "",4f))
//        foodList.add(HomeModel("Goreng", "",4.5f))
//    }

    override fun onClick(v: View, data: com.skiddie.dadsbakery.model.reponse.home.Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }


    override fun onHomeSuccess(homeResponse: com.skiddie.dadsbakery.model.reponse.home.HomeResponse) {

        for (a in homeResponse.data.indices){
            val items:List<String> = homeResponse.data[a].types?.split(",") ?: ArrayList()
            for (x in items.indices){
                when {
                    items[x].equals("new_food", true) -> {
                        newStateList.add(homeResponse.data[a])
                    }
                    items[x].equals("recommended", true) -> {
                        recomendedList.add(homeResponse.data[a])
                    }
                    items[x].equals("popular", true) -> {
                        popularList.add(homeResponse.data[a])
                    }
                }
            }
        }

        val adapter = HomeAdapter(homeResponse.data, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        sectionPagerAdapter.setData(newStateList, popularList, recomendedList)
        viewPager.adapter = sectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}