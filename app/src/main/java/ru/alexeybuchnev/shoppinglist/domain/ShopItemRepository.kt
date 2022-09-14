package ru.alexeybuchnev.shoppinglist.domain

interface ShopItemRepository {

    fun getShopList() : List<ShopItem>
    fun getShopItem(id: Int): ShopItem
    fun editShopItem(item: ShopItem)
    fun deleteShopItem(id: Int)
    fun addShopItem(item: ShopItem)

}