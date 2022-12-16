package com.padc.ponnya.groceryapp.data.models


import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.network.CloudFireStoreFirebaseApiImpl
import com.padc.ponnya.groceryapp.network.FirebaseApi

object GroceryModelImpl : GroceryModel {
    //override var mFirebaseApi: FirebaseApi = RealtimeDatabaseFirebaseApiImpl

    override var mFirebaseApi: FirebaseApi = CloudFireStoreFirebaseApiImpl


    override fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getGroceries(onSuccess, onFaiure)
    }

    override fun addGrocery(name: String, description: String, amount: Int) {
        mFirebaseApi.addGrocery(name, description, amount)
    }

    override fun deleteGrocery(name: String) {
        mFirebaseApi.deleteGrocery(name)
    }

}