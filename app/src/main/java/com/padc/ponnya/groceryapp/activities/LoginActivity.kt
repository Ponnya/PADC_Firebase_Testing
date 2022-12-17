package com.padc.ponnya.groceryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padc.ponnya.groceryapp.R
import com.padc.ponnya.groceryapp.databinding.ActivityLoginBinding
import com.padc.ponnya.groceryapp.mvp.presenters.LoginPresenter
import com.padc.ponnya.groceryapp.mvp.presenters.impls.LoginPresenterImpl
import com.padc.ponnya.groceryapp.mvp.views.LoginView

class LoginActivity : AbstractBaseActivity(), LoginView {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var mPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        setUpPresenter()
        setUpActionListeners()

        mPresenter.onUiReady(this)
    }

    private fun setUpActionListeners() {
        binding.btnLogin.setOnClickListener {
            mPresenter.onTapLogin(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.btnRegister.setOnClickListener {
            mPresenter.onTapRegister()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
    }

    override fun navigateToHomeScreen() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }
}