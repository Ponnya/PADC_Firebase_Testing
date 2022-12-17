package com.padc.ponnya.groceryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padc.ponnya.groceryapp.databinding.ActivityRegisterBinding
import com.padc.ponnya.groceryapp.mvp.presenters.RegisterPresenter
import com.padc.ponnya.groceryapp.mvp.presenters.impls.RegisterPresenterImpl
import com.padc.ponnya.groceryapp.mvp.views.RegisterView

class RegisterActivity : AbstractBaseActivity(), RegisterView {

    private lateinit var binding: ActivityRegisterBinding

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    private lateinit var mPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        binding.btnRegister.setOnClickListener {
            mPresenter.onTapRegister(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etUserName.text.toString()
            )
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    override fun navigateToToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }
}