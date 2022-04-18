package com.skiddie.dadsbakery.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.ivPoster
import kotlinx.android.synthetic.main.fragment_detail.tvTitle


class DetailFragment : Fragment() {

    var data: com.skiddie.dadsbakery.model.reponse.home.Data?= null
    var bundle: Bundle? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data?.let {
                initView(it)
            }
        }

        btnOrderNow.setOnClickListener {
        Navigation.findNavController(it).navigate(R.id.action_fragmenDetail_to_fragmenPayment, bundle)
        }
    }

    private fun initView(data: com.skiddie.dadsbakery.model.reponse.home.Data?) {

        bundle = bundleOf("data"  to data)

        data?.let {
            Glide.with(requireActivity())
                .load(it.picturePath)
                .into(ivPoster)

            tvTitle.text = it.name
            tvDesc.text = it.description
            tvIngredients.text = it.ingredients

            tvTotal.formatPrice(it.price.toString())
        }
    }

}