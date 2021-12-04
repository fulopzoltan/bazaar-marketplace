package com.example.bazaar_marketplace.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar_marketplace.R

class FareItemAdapter(private val FareItemList: List<FareDummy>) :
    RecyclerView.Adapter<FareItemAdapter.FareItemViewHolder>() {
    class FareItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemPrice: TextView = itemView.findViewById(R.id.fare_item_price)
        val sellerName: TextView = itemView.findViewById(R.id.fare_item_seller_name)
        val itemName: TextView = itemView.findViewById(R.id.fare_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FareItemViewHolder {
        val fareItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fare_item, parent, false)
        return FareItemViewHolder(fareItemView)
    }

    override fun onBindViewHolder(holder: FareItemViewHolder, position: Int) {
        val current = FareItemList[position]
        holder.itemName.text = current.itemName
        holder.itemPrice.text = "${current.price} ${current.unit}"
        holder.sellerName.text = current.sellerName
    }

    override fun getItemCount(): Int = FareItemList.size

}