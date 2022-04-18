package com.skiddie.dadsbakery.ui.home.newtaste


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_home_vertical.view.*


class HomeNewtasteAdapter (
    private val listData: List<com.skiddie.dadsbakery.model.reponse.home.Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
    ): RecyclerView.Adapter<HomeNewtasteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewtasteAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeNewtasteAdapter.ViewHolder, position: Int) {
       holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bind(data: com.skiddie.dadsbakery.model.reponse.home.Data, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                tvTitle.text = data.name
                tvPrice.formatPrice(data.price.toString())
                rbFood.rating = data.rate?.toFloat() ?: 0f

                Glide.with(context)
                        .load(data.picturePath)
                        .transform(RoundedCorners(20))
                        .into(ivPoster)

                itemView.setOnClickListener{itemAdapterCallback.onClick(it, data)}
            }
        }
    }


    interface ItemAdapterCallback{
        fun onClick(v: View, data: com.skiddie.dadsbakery.model.reponse.home.Data)
    }


}