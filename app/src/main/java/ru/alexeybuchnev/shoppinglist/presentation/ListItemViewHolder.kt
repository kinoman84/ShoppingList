package ru.alexeybuchnev.shoppinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.shoppinglist.R
import ru.alexeybuchnev.shoppinglist.domain.ShopItem

class ListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var shopItem: ShopItem? = null
    private val nameTextView = view.findViewById<TextView>(R.id.name_text_view)
    private val countTextView = view.findViewById<TextView>(R.id.count_text_view)

    fun bindShopItem(shopItem: ShopItem) {
        this.shopItem = shopItem

        nameTextView.text = shopItem.name
        countTextView.text = shopItem.count.toString()
    }
}