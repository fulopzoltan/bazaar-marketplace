package com.example.bazaar_marketplace.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bazaar_marketplace.R
import com.example.bazaar_marketplace.models.Product
import com.example.bazaar_marketplace.utils.remove
import com.example.bazaar_marketplace.utils.removeQuote
import com.example.bazaar_marketplace.utils.show

class FareItemAdapter(private var fareItemList: List<Product>, private var currentUser: String) :
    RecyclerView.Adapter<FareItemAdapter.FareItemViewHolder>() {

    class FareItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemPrice: TextView = itemView.findViewById(R.id.fare_item_price)
        val sellerName: TextView = itemView.findViewById(R.id.fare_item_seller_name)
        val itemName: TextView = itemView.findViewById(R.id.fare_item_name)
        val fareItemImage: ImageView = itemView.findViewById(R.id.fare_item_image)
        val orderButton: Button = itemView.findViewById(R.id.fare_item_order_button)
        val inactiveText: TextView = itemView.findViewById(R.id.fare_item_inactive)
        val activeText: TextView = itemView.findViewById(R.id.fare_item_active)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FareItemViewHolder {
        val fareItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fare_item, parent, false)
        return FareItemViewHolder(fareItemView)
    }

    override fun onBindViewHolder(holder: FareItemViewHolder, position: Int) {
        fun showButton() {
            holder.orderButton.show()
            holder.inactiveText.remove()
            holder.activeText.remove()
        }

        fun showActive() {
            holder.orderButton.remove()
            holder.inactiveText.remove()
            holder.activeText.show()
        }

        fun showInactive() {
            holder.orderButton.remove()
            holder.inactiveText.show()
            holder.activeText.remove()
        }

        val current = fareItemList[position]

        Glide.with(holder.itemView.context)
            .load(R.drawable.fare_item_image_placeholder)
            .placeholder(R.drawable.fare_item_image_placeholder)
            .error(R.drawable.fare_item_image_placeholder)
            .fitCenter()
            .centerInside()
            .circleCrop()
            .into(holder.fareItemImage)

        holder.itemName.text = current.title.removeQuote()
        holder.itemPrice.text =
            "${current.pricePerUnit.removeQuote()} ${current.priceType.removeQuote()}/ ${current.amountType.removeQuote()}"
        holder.sellerName.text = current.username.removeQuote()

        if (current.username == currentUser) {
            if (current.isActive) {
                showActive()
            } else {
                showInactive()
            }
        } else {
            showButton()
        }
    }

    override fun getItemCount(): Int = fareItemList.size

    fun setData(newList: List<Product>, currentUser: String) {
        fareItemList = newList
        this.currentUser = currentUser
    }


}