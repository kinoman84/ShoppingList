package ru.alexeybuchnev.shoppinglist.domain

import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(
    private val shopItemRepository: ShopItemRepository
) {

    suspend fun addShopItem(item: ShopItem) {
        shopItemRepository.addShopItem(item)
    }
}