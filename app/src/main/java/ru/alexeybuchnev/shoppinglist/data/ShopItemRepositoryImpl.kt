package ru.alexeybuchnev.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.alexeybuchnev.shoppinglist.domain.ShopItem
import ru.alexeybuchnev.shoppinglist.domain.ShopItemRepository
import java.lang.RuntimeException
import kotlin.random.Random

class ShopItemRepositoryImpl(
    application: Application
) : ShopItemRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override fun getShopList(): LiveData<List<ShopItem>> {
        return mutableLiveData
    }

    override fun getShopItem(id: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(id)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun editShopItem(item: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(item))
    }

    override fun deleteShopItem(id: Int) {
        shopListDao.deleteShopItem(id)
    }

    override fun addShopItem(item: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(item))
    }
}