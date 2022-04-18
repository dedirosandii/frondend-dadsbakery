package com.skiddie.dadsbakery.ui.order.pastorders


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.utils.Helpers.convertLongToTime
import com.skiddie.dadsbakery.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_inprogress.view.*
import kotlinx.android.synthetic.main.item_pastorders.view.*
import kotlinx.android.synthetic.main.item_pastorders.view.ivPoster
import kotlinx.android.synthetic.main.item_pastorders.view.tvPrice
import kotlinx.android.synthetic.main.item_pastorders.view.tvTitle


class PastordersAdapter (
    private val listData: List<com.skiddie.dadsbakery.model.reponse.transaction.Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
    ): RecyclerView.Adapter<PastordersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pastorders, parent, false)
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
                tvDate.text = data.food.createdAt.convertLongToTime("MMM dd , HH.MM")


                Glide.with(context)
                        .load(data.food.picturePath)
                        .into(ivPoster)

                if(data.status.equals("CANCELLED", true)){
                    tvCancelled.visibility = View.VISIBLE
                    tvCancelled.text = "Canceled"
                }else if(data.status.equals("DELIVERED", true)){
                    tvDelivered.visibility = View.VISIBLE
                    tvDelivered.text = "Delivered"
                }

                itemView.setOnClickListener{itemAdapterCallback.onClick(it, data)}
            }
        }
    }


    interface ItemAdapterCallback{
        fun onClick(v: View, data: com.skiddie.dadsbakery.model.reponse.transaction.Data)
    }


}