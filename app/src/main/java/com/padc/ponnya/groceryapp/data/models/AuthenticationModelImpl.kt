package com.padc.ponnya.groceryapp.data.models

import com.padc.ponnya.groceryapp.network.auth.AuthManager
import com.padc.ponnya.groceryapp.network.auth.FirebaseAuthManager


object AuthenticationModelImpl : AuthenticationModel {
    override var mAuthManager: AuthManager = FirebaseAuthManager

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email, password, onSuccess, onFailure)
    }

    override fun register(
        email: String,
        password: String,
        userName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.register(email, password, userName, onSuccess, onFailure)
    }

    override fun getUserName(): String {
        return mAuthManager.getUserName()
    }
}