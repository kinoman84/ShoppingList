package ru.alexeybuchnev.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.shoppinglist.R
import ru.alexeybuchnev.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {

    var shopItemList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {

        val layout = when (viewType) {
            DISABLE_LAYOUT_ID -> R.layout.item_shop_disabled
            ENABLE_LAYOUT_ID -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }

        val view =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = shopItemList[position]
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(item)
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(item.id)
        }
        holder.bindShopItem(item)
    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopItemList[position].isActive) {
            ENABLE_LAYOUT_ID
        } else {
            DISABLE_LAYOUT_ID
        }
    }

    class ListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var shopItem: ShopItem? = null
        val nameTextView = view.findViewById<TextView>(R.id.name_text_view)
        val countTextView = view.findViewById<TextView>(R.id.count_text_view)

        fun bindShopItem(shopItem: ShopItem) {
            this.shopItem = shopItem

            nameTextView.text = shopItem.name
            countTextView.text = shopItem.count.toString()
        }
    }

    companion object {
        const val DISABLE_LAYOUT_ID = 0
        const val ENABLE_LAYOUT_ID = 1
        const val MAX_POOL_SIZE = 10
    }
}