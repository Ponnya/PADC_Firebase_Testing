package com.padc.ponnya.groceryapp.data.models

import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.network.FirebaseApi

interface GroceryModel {
    var mFirebaseApi: FirebaseApi

    fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit)

    fun addGrocery(name: String, description: String, amount: Int)

    fun deleteGrocery(name: String)
}