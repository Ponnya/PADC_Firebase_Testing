package com.padc.ponnya.groceryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.ponnya.groceryapp.mvp.views.BaseView

interface BasePresenter<V : BaseView> {
    fun onUiReady(owner: LifecycleOwner)
    fun initView(view: V)

}