package com.padc.ponnya.groceryapp.data.models

import android.graphics.Bitmap
import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.network.FirebaseApi
import com.padc.ponnya.groceryapp.network.remoteconfig.FirebaseRemoteConfigManager

interface GroceryModel {
    var mFirebaseApi: FirebaseApi

    var mFirebaseRemoteConfigManager: FirebaseRemoteConfigManager

    fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit)

    fun addGrocery(name: String, description: String, amount: Int, image: String)

    fun deleteGrocery(name: String)

    fun uploadImageAndUpdateGrocery(grocery: GroceryVO, image: Bitmap)

    fun setUpRemoteConfigWithDefaultValues()

    fun fetchRemoteConfigs()

    fun getAppNameFromRemoteConfig(): String

    fun getVersion(): Int
}