package ru.alexeybuchnev.shoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val isActive: Boolean,
    var id: Int = NOT_INITIALIZED_ID
) {
    companion object {
        const val NOT_INITIALIZED_ID = -1
    }
}
