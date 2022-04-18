package com.skiddie.dadsbakery.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.skiddie.dadsbakery.ui.order.inprogress.InprogressFragment
import com.skiddie.dadsbakery.ui.order.pastorders.PastordersFragment

@Suppress("DEPRECATION")
class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var inprogressList:ArrayList<com.skiddie.dadsbakery.model.reponse.transaction.Data>? = ArrayList()
    private var pastordersList:ArrayList<com.skiddie.dadsbakery.model.reponse.transaction.Data>? = ArrayList()


    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
       return when(position){
           0 -> "In Progress"
           1 -> "Past Orders"
           else -> ""
       }
    }

    override fun getItem(position: Int): Fragment {
        val fragment : Fragment
        when(position){
            0 -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PastordersFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastordersList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    fun setData(inprogressListParms: ArrayList<com.skiddie.dadsbakery.model.reponse.transaction.Data>?, postordersListParms: ArrayList<com.skiddie.dadsbakery.model.reponse.transaction.Data>?){
        inprogressList = inprogressListParms
        pastordersList = postordersListParms
    }

}