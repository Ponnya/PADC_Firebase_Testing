package com.padc.ponnya.groceryapp.mvp.presenters

import android.content.Context
import com.padc.ponnya.groceryapp.mvp.views.LoginView

interface LoginPresenter : BasePresenter<LoginView> {
    fun onTapLogin(context: Context, email: String, password: String)
    fun onTapRegister()
}