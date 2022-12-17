package com.padc.ponnya.groceryapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padc.ponnya.groceryapp.mvp.views.BaseView

interface BasePresenter<V : BaseView> {
    fun onUiReady(context: Context, owner: LifecycleOwner)
    fun initView(view: V)
}