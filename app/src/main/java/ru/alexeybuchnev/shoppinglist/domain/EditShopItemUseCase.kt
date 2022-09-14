package ru.alexeybuchnev.shoppinglist.domain

class EditShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun editShopItem(item: ShopItem) {
        shopItemRepository.editShopItem(item)
    }
}