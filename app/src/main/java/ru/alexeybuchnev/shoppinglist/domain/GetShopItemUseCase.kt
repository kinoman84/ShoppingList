package ru.alexeybuchnev.shoppinglist.domain

import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(
    private val shopItemRepository: ShopItemRepository
) {

    suspend fun getShopItem(id: Int): ShopItem {
        return shopItemRepository.getShopItem(id)
    }
}