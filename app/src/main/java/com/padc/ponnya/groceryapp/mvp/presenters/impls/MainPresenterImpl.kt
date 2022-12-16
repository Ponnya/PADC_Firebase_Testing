package com.padc.grocery.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.grocery.mvp.presenters.MainPresenter
import com.padc.ponnya.groceryapp.data.models.GroceryModelImpl
import com.padc.ponnya.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padc.ponnya.groceryapp.mvp.views.MainView

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {
    private val mGroceryModel = GroceryModelImpl
    override fun onTapAddGrocery(name: String, description: String, amount: Int) {
        mGroceryModel.addGrocery(name, description, amount)
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mGroceryModel.getGroceries(
            onSuccess = {
                mView.showGroceryData(it)
            },
            onFaiure = {
                mView.showErrorMessage(it)
            }
        )
    }

    override fun onTapDeleteButton(name: String) {
        mGroceryModel.deleteGrocery(name)
    }

    override fun onTapEditGrocery(name: String, description: String, amount: Int) {
        mView.showGroceryDialog(name, description, amount.toString())
    }
}