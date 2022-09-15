package ru.alexeybuchnev.shoppinglist.domain

data class ShopItem(
    var name: String,
    var count: Int,
    var isActive: Boolean,
    var id: Int = NOT_INITIALIZED_ID
) {
    companion object {
        const val NOT_INITIALIZED_ID = -1
    }
}
