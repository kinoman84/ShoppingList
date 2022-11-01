package ru.alexeybuchnev.shoppinglist.data

import ru.alexeybuchnev.shoppinglist.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(entity: ShopItem) = ShopItemDbModel(
        id = entity.id,
        name = entity.name,
        enabled = entity.isActive,
        count = entity.count
    )

    fun mapDbModelToEntity(dbModel: ShopItemDbModel) = ShopItem(
        id = dbModel.id,
        name = dbModel.name,
        isActive = dbModel.enabled,
        count = dbModel.count
    )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}