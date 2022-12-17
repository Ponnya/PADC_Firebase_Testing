package com.padc.ponnya.groceryapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.ponnya.groceryapp.data.models.AuthenticationModel
import com.padc.ponnya.groceryapp.data.models.AuthenticationModelImpl
import com.padc.ponnya.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padc.ponnya.groceryapp.mvp.presenters.LoginPresenter
import com.padc.ponnya.groceryapp.mvp.views.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

    override fun onUiReady(owner: LifecycleOwner) {}

    override fun onTapLogin(email: String, password: String) {
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