package ru.alexeybuchnev.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.shopList.observe(this) {
            adapter.shopItemList = it
        }
    }

    private fun setupRecyclerView() {

        adapter = ListAdapter()

        findViewById<RecyclerView>(R.id.shop_list_recycler_view).apply {
            adapter = this@MainActivity.adapter
            recycledViewPool.setMaxRecycledViews(
                ListAdapter.ENABLE_LAYOUT_ID,
                ListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ListAdapter.DISABLE_LAYOUT_ID,
                ListAdapter.MAX_POOL_SIZE
            )
        }
        adapter.onShopItemLongClickListener = {
            mainViewModel.changeStatus(it)
        }


    }
}