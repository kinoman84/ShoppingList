package ru.alexeybuchnev.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.alexeybuchnev.shoppinglist.R
import ru.alexeybuchnev.shoppinglist.domain.ShopItem

class ShopListAdapter :
    ListAdapter<ShopItem, ListItemViewHolder>(ShopItemDiffCallback()) {

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
        val item = getItem(position)
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(item)
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(item.id)
        }
        holder.bindShopItem(item)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isActive) {
            ENABLE_LAYOUT_ID
        } else {
            DISABLE_LAYOUT_ID
        }
    }

    companion object {
        const val DISABLE_LAYOUT_ID = 0
        const val ENABLE_LAYOUT_ID = 1
        const val MAX_POOL_SIZE = 10
    }
}