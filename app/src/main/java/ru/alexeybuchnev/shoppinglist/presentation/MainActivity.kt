package ru.alexeybuchnev.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
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

        val simpleSwipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction) {
                    ItemTouchHelper.LEFT -> {

                        val itemId = (viewHolder as ListAdapter.ListItemViewHolder).shopItem?.id
                        itemId?.let {
                            mainViewModel.deleteShopItem(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }


        val recyclerView = findViewById<RecyclerView>(R.id.shop_list_recycler_view)

        recyclerView.apply {
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

        ItemTouchHelper(simpleSwipe).apply {
            attachToRecyclerView(recyclerView)
        }

        adapter.onShopItemLongClickListener = {
            mainViewModel.changeStatus(it)
        }
        adapter.onShopItemClickListener = {
            Toast.makeText(this, "item $it clicked", Toast.LENGTH_SHORT).show()
        }



    }
}