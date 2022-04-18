package com.skiddie.dadsbakery.ui.order.inprogress


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.utils.Helpers.convertLongToTime
import com.skiddie.dadsbakery.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.fragment_orders_detail.*
import kotlinx.android.synthetic.main.item_inprogress.view.*
import kotlinx.android.synthetic.main.item_inprogress.view.ivPoster
import kotlinx.android.synthetic.main.item_inprogress.view.tvPrice
import kotlinx.android.synthetic.main.item_inprogress.view.tvTitle
import kotlinx.android.synthetic.main.item_pastorders.view.*

class InprogressAdapter (
    private val listData: List<com.skiddie.dadsbakery.model.reponse.transaction.Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
    ): RecyclerView.Adapter<InprogressAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_inprogress, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bind(data: com.skiddie.dadsbakery.model.reponse.transaction.Data, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                tvTitle.text = data.food.name
                tvPrice.formatPrice(data.food.price.toString())
                tvDateOnDelivery.text = data.food.createdAt.convertLongToTime("MMM dd , HH.MM")


                Glide.with(context)
                        .load(data.food.picturePath)
                        .into(ivPoster)
                if(data.status.equals("ON_DELIVERY", true)){
                    tvOnDelivery.visibility = View.VISIBLE
                    tvOnDelivery.text = "On Delivery"
                }else if(data.status.equals("PENDING", true)){
                    tvOnDelivery.visibility = View.VISIBLE
                    tvOnDelivery.text = "Pending"
                }

                itemView.setOnClickListener{itemAdapterCallback.onClick(it, data)}
            }
        }
    }


    interface ItemAdapterCallback{
        fun onClick(v: View, data: com.skiddie.dadsbakery.model.reponse.transaction.Data)
    }


}