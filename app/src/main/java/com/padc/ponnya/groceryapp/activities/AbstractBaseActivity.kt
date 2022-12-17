package com.padc.ponnya.groceryapp.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.padc.ponnya.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padc.ponnya.groceryapp.mvp.views.BaseView

abstract class AbstractBaseActivity : AppCompatActivity(), BaseView {

    inline fun <reified T : AbstractBasePresenter<W>, reified W : BaseView> getPresenter(): T {
        val presenter = ViewModelProvider(this)[T::class.java]
        if (this is W) presenter.initView(this)
        return presenter
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }
}