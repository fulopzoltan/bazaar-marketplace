package com.example.bazaar_marketplace.interfaces

import com.example.bazaar_marketplace.models.Product

interface FareItemClickListeners {
    fun onDeleteClicked(pos: Int, productId: String)
    fun onCardClicked(product: Product)
}