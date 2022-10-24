package ru.alexeybuchnev.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.alexeybuchnev.shoppinglist.R

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter
    private var shopItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shopItemContainer = findViewById(R.id.shop_item_container)

        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.submitList(it)
        }

        val button = findViewById<FloatingActionButton>(R.id.add_shop_item_button)
        button.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddMode(this)
                startActivity(intent)
            } else {
                launceFragment(ShopItemFragment.newInstanceAddMode())
            }
        }
    }

    override fun onEditingFinished() {
        supportFragmentManager.popBackStack()
    }

    private fun isOnePaneMode(): Boolean {
        return shopItemContainer == null
    }

    private fun launceFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {

        adapter = ShopListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.shop_list_recycler_view)
        recyclerView.apply {
            adapter = this@MainActivity.adapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.ENABLE_LAYOUT_ID,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.DISABLE_LAYOUT_ID,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        setSwipeListener(recyclerView)
        setLongClickListener()
        setClickListener()
    }

    private fun setSwipeListener(recyclerView: RecyclerView) {
        val simpleSwipeCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item.id)
            }
        }

        ItemTouchHelper(simpleSwipeCallback).apply {
            attachToRecyclerView(recyclerView)
        }
    }

    private fun setClickListener() {
        adapter.onShopItemClickListener = {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentEditMode(this, it)
                startActivity(intent)
            } else {
                launceFragment(ShopItemFragment.newInstanceEditMode(it))
            }
        }
    }

    private fun setLongClickListener() {
        adapter.onShopItemLongClickListener = {
            viewModel.changeStatus(it)
        }
    }
}