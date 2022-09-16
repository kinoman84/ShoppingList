package ru.alexeybuchnev.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ru.alexeybuchnev.shoppinglist.R
import ru.alexeybuchnev.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var listItemsLeanerLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listItemsLeanerLayout = findViewById(R.id.shop_list_linear_layout)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.shopList.observe(this) {
            drawShopList(it)
        }
    }


    fun drawShopList(list: List<ShopItem>) {

        listItemsLeanerLayout.removeAllViews()

        for (item in list) {
            val viewResId = if (item.isActive) {
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater.from(this).inflate(viewResId, listItemsLeanerLayout, false)
            view.findViewById<TextView>(R.id.name_text_view).text = item.name
            view.findViewById<TextView>(R.id.count_text_view).text = item.count.toString()
            view.setOnLongClickListener {
                mainViewModel.changeStatus(item)
                true
            }
            listItemsLeanerLayout.addView(view)
        }

    }
}