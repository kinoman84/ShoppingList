package ru.alexeybuchnev.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopItemRepository {

    fun getShopList() : LiveData<List<ShopItem>>
    suspend fun getShopItem(id: Int): ShopItem
    suspend fun editShopItem(item: ShopItem)
    suspend fun deleteShopItem(id: Int)
    suspend fun addShopItem(item: ShopItem)

}