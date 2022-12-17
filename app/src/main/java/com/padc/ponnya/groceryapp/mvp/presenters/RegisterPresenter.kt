package com.padc.ponnya.groceryapp.mvp.presenters

import android.content.Context
import com.padc.ponnya.groceryapp.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(context: Context, email: String, password: String, userName: String)
}