package com.padc.ponnya.groceryapp.mvp.presenters

import com.padc.ponnya.groceryapp.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(email: String, password: String, userName: String)
}