package ru.alexeybuchnev.shoppinglist.domain

class AddShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun addShopItem(item: ShopItem) {
        shopItemRepository.addShopItem(item)
    }
}