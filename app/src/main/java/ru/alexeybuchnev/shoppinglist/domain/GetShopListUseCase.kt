package ru.alexeybuchnev.shoppinglist.domain

class GetShopListUseCase(private val itemShopItemRepository: ShopItemRepository) {

    fun getShopList(): List<ShopItem> {
        return itemShopItemRepository.getShopList()
    }
}