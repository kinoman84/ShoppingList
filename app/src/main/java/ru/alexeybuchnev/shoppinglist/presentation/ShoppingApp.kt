package ru.alexeybuchnev.shoppinglist.presentation

import android.app.Application
import ru.alexeybuchnev.shoppinglist.di.DaggerApplicationComponent

class ShoppingApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}