package ru.alexeybuchnev.shoppinglist.domain

import javax.inject.Inject

class DeleteShopItemUseCase @Inject constructor(
    private val shopItemRepository: ShopItemRepository
) {

    suspend fun deleteShopItem(id: Int) {
        shopItemRepository.deleteShopItem(id)
    }
}