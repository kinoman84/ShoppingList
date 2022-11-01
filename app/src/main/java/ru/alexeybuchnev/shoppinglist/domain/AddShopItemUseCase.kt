package ru.alexeybuchnev.shoppinglist.domain

class AddShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    suspend fun addShopItem(item: ShopItem) {
        shopItemRepository.addShopItem(item)
    }
}