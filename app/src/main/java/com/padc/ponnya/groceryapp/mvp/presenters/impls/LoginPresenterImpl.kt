package com.padc.ponnya.groceryapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padc.ponnya.groceryapp.analytics.PARAMETER_EMAIL
import com.padc.ponnya.groceryapp.analytics.SCREEN_LOGIN
import com.padc.ponnya.groceryapp.analytics.TAP_LOGIN
import com.padc.ponnya.groceryapp.data.models.AuthenticationModel
import com.padc.ponnya.groceryapp.data.models.AuthenticationModelImpl
import com.padc.ponnya.groceryapp.data.models.GroceryModel
import com.padc.ponnya.groceryapp.data.models.GroceryModelImpl
import com.padc.ponnya.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padc.ponnya.groceryapp.mvp.presenters.LoginPresenter
import com.padc.ponnya.groceryapp.mvp.views.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl
    private val mGroceryModel: GroceryModel = GroceryModelImpl

    override fun onUiReady(
        context: Context,
        owner: LifecycleOwner
    ) {
        sendEventsToFirebaseAnalytics(context, SCREEN_LOGIN)
        mGroceryModel.setUpRemoteConfigWithDefaultValues()
        mGroceryModel.fetchRemoteConfigs()
    }

    override fun onTapLogin(context: Context, email: String, password: String) {
        sendEventsToFirebaseAnalytics(context, TAP_LOGIN, PARAMETER_EMAIL, email)
        mAuthenticatioModel.login(email, password, onSuccess = {
            mView.navigateToHomeScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onTapRegister() {
        mView.navigateToRegisterScreen()
    }
}