package ru.alexeybuchnev.shoppinglist.domain

class DeleteShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    suspend fun deleteShopItem(id: Int) {
        shopItemRepository.deleteShopItem(id)
    }
}