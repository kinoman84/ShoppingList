package ru.alexeybuchnev.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.shoppinglist.R
import ru.alexeybuchnev.shoppinglist.domain.ShopItem

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {

    var list = listOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_shop_enabled, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = list[position]
        holder.nameTextView.text = item.name
        holder.countTextView.text = item.count.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.name_text_view)
        val countTextView = view.findViewById<TextView>(R.id.count_text_view)
    }
}