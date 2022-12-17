package com.padc.ponnya.groceryapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.ponnya.groceryapp.data.models.AuthenticationModel
import com.padc.ponnya.groceryapp.data.models.AuthenticationModelImpl
import com.padc.ponnya.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padc.ponnya.groceryapp.mvp.presenters.RegisterPresenter
import com.padc.ponnya.groceryapp.mvp.views.RegisterView

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    override fun onTapRegister(email: String, password: String, userName: String) {
        mAuthenticationModel.register(email, password, userName, onSuccess = {
            mView.navigateToToLoginScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onUiReady(owner: LifecycleOwner) {}
}