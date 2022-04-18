package com.skiddie.dadsbakery.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.skiddie.dadsbakery.ui.home.newtaste.HomeNewTasteFragment
import com.skiddie.dadsbakery.ui.home.popular.HomePopularFragment
import com.skiddie.dadsbakery.ui.home.recommended.HomeRecommendedFragment

@Suppress("DEPRECATION")
class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var newTasteList: ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data>? = ArrayList()
    private var popularList: ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data>? = ArrayList()
    private var recommendedList:ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data>? = ArrayList()


    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
       return when(position){
           0 -> "New Taste"
           1 -> "Popular"
           2 -> "Recommended"
           else -> "Recommended"
       }
    }

    override fun getItem(position: Int): Fragment {
        val fragment : Fragment
        when(position){
            0 -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = HomePopularFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", popularList)
                fragment.arguments = bundle
                return fragment
            }
            2 -> {
                fragment = HomeRecommendedFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", recommendedList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    fun setData(newTasteListParms: ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data>?, popularListParms: ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data>?, recommendedListParms: ArrayList<com.skiddie.dadsbakery.model.reponse.home.Data>?){
        newTasteList = newTasteListParms
        popularList = popularListParms
        recommendedList = recommendedListParms
    }
}