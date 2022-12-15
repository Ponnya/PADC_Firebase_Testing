package com.padc.ponnya.groceryapp.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padc.ponnya.groceryapp.mvp.views.BaseView

abstract class AbstractBasePresenter<V : BaseView> : BasePresenter<V>, ViewModel() {
    protected lateinit var mView: V

    override fun initView(view: V) {
        mView = view
    }
}