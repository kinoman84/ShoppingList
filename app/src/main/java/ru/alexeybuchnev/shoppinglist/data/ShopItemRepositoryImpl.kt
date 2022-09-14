package ru.alexeybuchnev.shoppinglist.data

import ru.alexeybuchnev.shoppinglist.domain.ShopItem
import ru.alexeybuchnev.shoppinglist.domain.ShopItemRepository
import java.lang.RuntimeException

object ShopItemRepositoryImpl : ShopItemRepository {

    private val shoppingList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    override fun getShopList(): List<ShopItem> {
        return shoppingList.toList()
    }

    override fun getShopItem(id: Int): ShopItem {
        return shoppingList.find { it.id == id } ?: throw RuntimeException("item id $id not find")
    }

    override fun editShopItem(item: ShopItem) {
        deleteShopItem(item.id)
        addShopItem(item)
    }

    override fun deleteShopItem(id: Int) {
        val element = getShopItem(id)
        shoppingList.remove(element)
    }

    override fun addShopItem(item: ShopItem) {
        if (item.id == ShopItem.NOT_INITIALIZED_ID) {
            item.id = autoIncrementId++
        }
        shoppingList.add(item)
    }
}