package ru.alexeybuchnev.shoppinglist.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.alexeybuchnev.shoppinglist.data.AppDatabase
import ru.alexeybuchnev.shoppinglist.data.ShopItemRepositoryImpl
import ru.alexeybuchnev.shoppinglist.data.ShopListDao
import ru.alexeybuchnev.shoppinglist.domain.ShopItemRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopItemRepository(impl: ShopItemRepositoryImpl) : ShopItemRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}