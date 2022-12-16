package com.padc.ponnya.groceryapp.network

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.utils.AMOUNT_FIELD
import com.padc.ponnya.groceryapp.utils.DESCRIPTION_FIELD
import com.padc.ponnya.groceryapp.utils.NAME_FIELD


object CloudFireStoreFirebaseApiImpl : FirebaseApi {
    private val db = Firebase.firestore
    override fun getGroceries(
        onSuccess: (groceries: List<GroceryVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
        db.collection("Groceries")
            .get()
            .addOnSuccessListener { result ->
                val groceryList = arrayListOf<GroceryVO>()
                for (document in result) {
                    val data = document.data
                    var grocery = GroceryVO(
                        name = data[NAME_FIELD] as String,
                        description = data[DESCRIPTION_FIELD] as String,
                        amount = (data[AMOUNT_FIELD] as Long).toInt()
                    )
                    groceryList.add(grocery)

                }
                onSuccess(groceryList)
            }
            .addOnFailureListener {
                onFialure(it.message ?: "Please check connection")
            }
    }

    override fun addGrocery(name: String, description: String, amount: Int) {

    }

    override fun deleteGrocery(name: String) {

    }

}