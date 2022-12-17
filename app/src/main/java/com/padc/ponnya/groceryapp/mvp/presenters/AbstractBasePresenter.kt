package com.padc.ponnya.groceryapp.mvp.presenters

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.padc.ponnya.groceryapp.mvp.views.BaseView

abstract class AbstractBasePresenter<V : BaseView> : BasePresenter<V>, ViewModel() {
    protected lateinit var mView: V

    override fun initView(view: V) {
        mView = view
    }

    fun sendEventsToFirebaseAnalytics(
        context: Context,
        eventName: String,
        key: String = "",
        value: String = "",
    ) {
        if (key.isNotEmpty() && value.isNotEmpty()) {
            FirebaseAnalytics.getInstance(context).logEvent(eventName, buildBundle(key, value))
        } else {
            FirebaseAnalytics.getInstance(context).logEvent(eventName, null)
        }
    }

    private fun buildBundle(key: String, value: String): Bundle {
        val bundle = Bundle()
        bundle.putString(key, value)
        return bundle
    }
}