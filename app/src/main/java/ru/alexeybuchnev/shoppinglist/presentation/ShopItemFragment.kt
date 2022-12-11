package ru.alexeybuchnev.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import ru.alexeybuchnev.shoppinglist.R
import ru.alexeybuchnev.shoppinglist.domain.ShopItem
import javax.inject.Inject

class ShopItemFragment(): Fragment() {

    private lateinit var viewModel: ShopItemViewModel
    private lateinit var nameTextInputLayout: TextInputLayout
    private lateinit var countTextInputLayout: TextInputLayout
    private lateinit var nameEditText: EditText
    private lateinit var countEditText: EditText
    private lateinit var saveButton: Button

    private var screenMode: String = UNKNOWN_MODE
    private var shopItemId: Int = ShopItem.NOT_INITIALIZED_ID

    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    private val component by lazy {
        (requireActivity().application as ShoppingApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        if (context is OnEditingFinishedListener){
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("must implements OnEditingFinishedListener")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[ShopItemViewModel::class.java]
        when(screenMode) {
            ADD_MODE -> launceAddMode()
            EDIT_MODE -> launceEditMode()
        }
        observeLiveData()
        resetErrorInputHint()
    }

    private fun launceAddMode() {
        saveButton.setOnClickListener {
            val name = nameEditText.text?.toString()
            val count = countEditText.text?.toString()
            viewModel.addShopItem(name, count)
        }
    }

    private fun launceEditMode() {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(viewLifecycleOwner) {
            nameEditText.setText(it.name)
            countEditText.setText(it.count.toString())
        }
        saveButton.setOnClickListener {
            val name = nameEditText.text?.toString()
            val count = countEditText.text?.toString()
            viewModel.editItem(name, count)
        }
    }

    private fun observeLiveData() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name_text)
            } else {
                 null
            }
            nameTextInputLayout.error = message
        }
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count_text)
            } else {
                null
            }
            countTextInputLayout.error = message
        }
    }

    private fun resetErrorInputHint() {
        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        countEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != ADD_MODE && mode != EDIT_MODE) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == EDIT_MODE) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = args.getInt(SHOP_ITEM_ID,ShopItem.NOT_INITIALIZED_ID)
        }
    }

    private fun initViews(view: View) {
        nameTextInputLayout = view.findViewById(R.id.name_text_input_layout)
        countTextInputLayout = view.findViewById(R.id.count_text_input_layout)
        nameEditText = view.findViewById(R.id.name_edit_text)
        countEditText = view.findViewById(R.id.count_edit_text)
        saveButton = view.findViewById(R.id.save_button)
    }

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val SHOP_ITEM_ID = "shop_item_id"
        private const val ADD_MODE = "add_item"
        private const val EDIT_MODE = "edit_item"
        private const val UNKNOWN_MODE = ""

        fun newInstanceAddMode() : ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, ADD_MODE)
                }
            }
        }

        fun newInstanceEditMode(shopItemId: Int) : ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, EDIT_MODE)
                    putInt(SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }
}