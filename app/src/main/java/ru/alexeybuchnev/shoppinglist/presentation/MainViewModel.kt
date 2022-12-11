package ru.alexeybuchnev.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexeybuchnev.shoppinglist.domain.DeleteShopItemUseCase
import ru.alexeybuchnev.shoppinglist.domain.EditShopItemUseCase
import ru.alexeybuchnev.shoppinglist.domain.GetShopListUseCase
import ru.alexeybuchnev.shoppinglist.domain.ShopItem
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase
) : ViewModel() {

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(id: Int) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(id)
        }
    }

    fun changeStatus(item: ShopItem) {
        viewModelScope.launch {
            val newItem = item.copy(isActive = !item.isActive)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}