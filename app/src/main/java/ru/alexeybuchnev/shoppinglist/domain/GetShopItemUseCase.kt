package ru.alexeybuchnev.shoppinglist.domain

class GetShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getShopItem(id: Int): ShopItem {
        return shopItemRepository.getShopItem(id)
    }
}