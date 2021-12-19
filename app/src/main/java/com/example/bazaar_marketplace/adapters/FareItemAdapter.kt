package com.example.bazaar_marketplace.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar_marketplace.R
import com.example.bazaar_marketplace.models.Product

class FareItemAdapter(private var fareItemList: List<Product>) :
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
        val current = fareItemList[position]
        holder.itemName.text = current.title
        holder.itemPrice.text =
            "${current.pricePerUnit} ${current.priceType}/ ${current.amountType}"
        holder.sellerName.text = current.username
    }

    override fun getItemCount(): Int = fareItemList.size

    fun setData(newList: List<Product>) {
        fareItemList = newList
    }
}