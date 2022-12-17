package com.padc.grocery.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.padc.grocery.mvp.presenters.MainPresenter
import com.padc.ponnya.groceryapp.analytics.SCREEN_HOME
import com.padc.ponnya.groceryapp.data.models.AuthenticationModel
import com.padc.ponnya.groceryapp.data.models.AuthenticationModelImpl
import com.padc.ponnya.groceryapp.data.models.GroceryModelImpl
import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padc.ponnya.groceryapp.mvp.views.MainView

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {
    private val mGroceryModel = GroceryModelImpl
    private var mChosenGroceryForFileUpload: GroceryVO? = null

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

    override fun onTapAddGrocery(name: String, description: String, amount: Int) {
        mGroceryModel.addGrocery(name, description, amount, "")
    }

    override fun onPhotoTaken(bitmap: Bitmap) {
        mChosenGroceryForFileUpload?.let {
            mGroceryModel.uploadImageAndUpdateGrocery(it, bitmap)
        }
    }

    override fun onUiReady(
        context: Context,
        owner: LifecycleOwner
    ) {
        sendEventsToFirebaseAnalytics(context, SCREEN_HOME)
        //Show Current User Name
        mView.showUserName(
            mAuthenticatioModel.getUserName()
        )
        mView.displayToolbarTitle(mGroceryModel.getAppNameFromRemoteConfig())
        mView.showRecyclerView(mGroceryModel.getVersion())

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

    override fun onTapFileUpload(grocery: GroceryVO) {
        mChosenGroceryForFileUpload = grocery
        mView.openGallery()
    }
}