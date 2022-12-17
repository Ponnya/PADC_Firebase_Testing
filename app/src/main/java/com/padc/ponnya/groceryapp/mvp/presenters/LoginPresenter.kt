package com.padc.ponnya.groceryapp.mvp.presenters

import com.padc.ponnya.groceryapp.mvp.views.LoginView

interface LoginPresenter : BasePresenter<LoginView> {
    fun onTapLogin(email: String, password: String)
    fun onTapRegister()
}