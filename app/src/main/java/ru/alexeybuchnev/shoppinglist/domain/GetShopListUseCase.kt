package ru.alexeybuchnev.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val itemShopItemRepository: ShopItemRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return itemShopItemRepository.getShopList()
    }
}