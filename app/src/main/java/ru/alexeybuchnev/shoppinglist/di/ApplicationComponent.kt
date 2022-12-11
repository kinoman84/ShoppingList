package ru.alexeybuchnev.shoppinglist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.alexeybuchnev.shoppinglist.presentation.MainActivity
import ru.alexeybuchnev.shoppinglist.presentation.ShopItemFragment
import ru.alexeybuchnev.shoppinglist.presentation.ShoppingApp

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: ShoppingApp)
    fun inject(mainActivity: MainActivity)
    fun inject(shopItemFragment: ShopItemFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}