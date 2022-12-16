package com.padc.grocery.mvp.presenters

import android.graphics.Bitmap
import com.padc.ponnya.groceryapp.delegates.GroceryViewItemActionDelegate
import com.padc.ponnya.groceryapp.mvp.presenters.BasePresenter
import com.padc.ponnya.groceryapp.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView>, GroceryViewItemActionDelegate {
    fun onTapAddGrocery(name: String, description: String, amount: Int)
    fun onPhotoTaken(bitmap: Bitmap)
}