package com.padc.ponnya.groceryapp.network

import com.padc.ponnya.groceryapp.data.vos.GroceryVO


object CloudFireStoreFirebaseApiImpl : FirebaseApi {
    override fun getGroceries(
        onSuccess: (groceries: List<GroceryVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {

    }

    override fun addGrocery(name: String, description: String, amount: Int) {

    }

    override fun deleteGrocery(name: String) {

    }

}