package com.padc.grocery.mvp.presenters

import com.padc.ponnya.groceryapp.mvp.presenters.BasePresenter
import com.padc.ponnya.groceryapp.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView> {
    fun onTapAddGrocery(name: String, description: String, amount: Int)
}