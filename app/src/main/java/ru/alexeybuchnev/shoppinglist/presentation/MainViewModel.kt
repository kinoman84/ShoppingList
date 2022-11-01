package ru.alexeybuchnev.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.alexeybuchnev.shoppinglist.data.ShopItemRepositoryImpl
import ru.alexeybuchnev.shoppinglist.domain.DeleteShopItemUseCase
import ru.alexeybuchnev.shoppinglist.domain.EditShopItemUseCase
import ru.alexeybuchnev.shoppinglist.domain.GetShopListUseCase
import ru.alexeybuchnev.shoppinglist.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopItemRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

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