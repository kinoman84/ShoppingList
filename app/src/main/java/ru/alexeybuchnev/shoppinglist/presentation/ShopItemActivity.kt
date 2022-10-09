package ru.alexeybuchnev.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import ru.alexeybuchnev.shoppinglist.R
import ru.alexeybuchnev.shoppinglist.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

//    private lateinit var viewModel: ShopItemViewModel
//    private lateinit var nameTextInputLayout: TextInputLayout
//    private lateinit var countTextInputLayout: TextInputLayout
//    private lateinit var nameEditText: EditText
//    private lateinit var countEditText: EditText
//    private lateinit var saveButton: Button
//
//    private var screenMode = UNKNOWN_MODE
//    private var shopItemId = ShopItem.NOT_INITIALIZED_ID


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
//        parseIntent()
//        initViews()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        when(screenMode) {
//            ADD_MODE -> launceAddMode()
//            EDIT_MODE -> launceEditMode()
//        }
//        observeLiveData()
//        resetErrorInputHint()
    }
//
//    private fun launceAddMode() {
//        saveButton.setOnClickListener {
//            val name = nameEditText.text?.toString()
//            val count = countEditText.text?.toString()
//            viewModel.addShopItem(name, count)
//        }
//    }
//
//    private fun launceEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this) {
//            nameEditText.setText(it.name)
//            countEditText.setText(it.count.toString())
//        }
//        saveButton.setOnClickListener {
//            val name = nameEditText.text?.toString()
//            val count = countEditText.text?.toString()
//            viewModel.editItem(name, count)
//        }
//    }
//
//    private fun observeLiveData() {
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//        viewModel.errorInputName.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_name_text)
//            } else {
//                 null
//            }
//            nameTextInputLayout.error = message
//        }
//        viewModel.errorInputCount.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_count_text)
//            } else {
//                null
//            }
//            countTextInputLayout.error = message
//        }
//    }
//
//    private fun resetErrorInputHint() {
//        nameEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//        countEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Param screen mode is absent")
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != ADD_MODE && mode != EDIT_MODE) {
//            throw RuntimeException("Unknown screen mode $mode")
//        }
//        screenMode = mode
//        if (screenMode == EDIT_MODE) {
//            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
//                throw RuntimeException("Param shop item id is absent")
//            }
//            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.NOT_INITIALIZED_ID)
//        }
//    }
//
//    private fun initViews() {
//        nameTextInputLayout = findViewById(R.id.name_text_input_layout)
//        countTextInputLayout = findViewById(R.id.count_text_input_layout)
//        nameEditText = findViewById(R.id.name_edit_text)
//        countEditText = findViewById(R.id.count_edit_text)
//        saveButton = findViewById(R.id.save_button)
//    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "shop_item_id"
        private const val ADD_MODE = "add_item"
        private const val EDIT_MODE = "edit_item"
        private const val UNKNOWN_MODE = ""

        fun newIntentAddMode(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, ADD_MODE)
            return intent
        }

        fun newIntentEditMode(context: Context, itemShopId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, EDIT_MODE)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, itemShopId)
            return intent
        }
    }
}