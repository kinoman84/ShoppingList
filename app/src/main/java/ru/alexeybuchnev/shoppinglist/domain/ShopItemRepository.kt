package ru.alexeybuchnev.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopItemRepository {

    fun getShopList() : LiveData<List<ShopItem>>
    fun getShopItem(id: Int): ShopItem
    fun editShopItem(item: ShopItem)
    fun deleteShopItem(id: Int)
    fun addShopItem(item: ShopItem)

}