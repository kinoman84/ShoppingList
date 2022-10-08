package ru.alexeybuchnev.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.alexeybuchnev.shoppinglist.domain.ShopItem
import ru.alexeybuchnev.shoppinglist.domain.ShopItemRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopItemRepositoryImpl : ShopItemRepository {

    private val shoppingList = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id) })
    private val mutableLiveData = MutableLiveData<List<ShopItem>>(emptyList())

    private var autoIncrementId = 0

    init {
        for (item in 1 until  10) {
            val shopItem = ShopItem(item.toString(), item, Random.nextBoolean())
            addShopItem(shopItem)
        }
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return mutableLiveData
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
        updateListLiveData()
    }

    override fun addShopItem(item: ShopItem) {
        if (item.id == ShopItem.NOT_INITIALIZED_ID) {
            item.id = autoIncrementId++
        }
        shoppingList.add(item)
        updateListLiveData()
    }

    private fun updateListLiveData(){
        mutableLiveData.value = shoppingList.toList()
    }
}