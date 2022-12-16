package com.padc.ponnya.groceryapp.network

import android.graphics.Bitmap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.utils.AMOUNT_FIELD
import com.padc.ponnya.groceryapp.utils.DESCRIPTION_FIELD
import com.padc.ponnya.groceryapp.utils.GROCERIES_COLLECTION
import com.padc.ponnya.groceryapp.utils.NAME_FIELD


object CloudFireStoreFirebaseApiImpl : FirebaseApi {
    private val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    override fun getGroceries(
        onSuccess: (groceries: List<GroceryVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
        /* db.collection(GROCERIES_COLLECTION)
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
             }*/

        db.collection(GROCERIES_COLLECTION)
            .addSnapshotListener { value, error ->
                error?.let { onFialure(it.message ?: "Please check connection") } ?: kotlin.run {
                    val groceryList = arrayListOf<GroceryVO>()
                    val result = value?.documents ?: listOf()

                    for (document in result) {
                        val data = document.data ?: mapOf()
                        val grocery = GroceryVO(
                            name = data[NAME_FIELD] as String,
                            description = data[DESCRIPTION_FIELD] as String,
                            amount = (data[AMOUNT_FIELD] as Long).toInt()
                        )
                        groceryList.add(grocery)
                    }
                    onSuccess(groceryList)
                }
            }
    }

    override fun addGrocery(name: String, description: String, amount: Int, image: String) {
        val groceryMap = hashMapOf(
            NAME_FIELD to name,
            DESCRIPTION_FIELD to description,
            AMOUNT_FIELD to amount
        )

        db.collection(GROCERIES_COLLECTION)
            .document(name)
            .set(groceryMap)
    }

    override fun deleteGrocery(name: String) {
        db.collection(GROCERIES_COLLECTION)
            .document(name)
            .delete()
    }

    override fun uploadImageAndEditGrocery(image: Bitmap, grocery: GroceryVO) {
        TODO("Not yet implemented")
    }

}