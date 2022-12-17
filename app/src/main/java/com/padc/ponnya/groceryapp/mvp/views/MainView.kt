package com.padc.ponnya.groceryapp.mvp.views

import com.padc.ponnya.groceryapp.data.vos.GroceryVO

interface MainView : BaseView {
    fun showUserName(name: String)
    fun showGroceryData(groceryList: List<GroceryVO>)
    fun showGroceryDialog(name: String, description: String, amount: String)
    fun showErrorMessage(message: String)
    fun openGallery()
}