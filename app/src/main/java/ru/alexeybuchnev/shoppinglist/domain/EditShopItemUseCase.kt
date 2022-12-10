package ru.alexeybuchnev.shoppinglist.domain

import javax.inject.Inject

class EditShopItemUseCase @Inject constructor(
    private val shopItemRepository: ShopItemRepository
) {

    suspend fun editShopItem(item: ShopItem) {
        shopItemRepository.editShopItem(item)
    }
}