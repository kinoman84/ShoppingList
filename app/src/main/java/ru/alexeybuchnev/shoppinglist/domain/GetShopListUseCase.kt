package ru.alexeybuchnev.shoppinglist.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(
    private val itemShopItemRepository: ShopItemRepository
) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return itemShopItemRepository.getShopList()
    }
}