package com.padc.ponnya.groceryapp.delegates

import com.padc.ponnya.groceryapp.data.vos.GroceryVO

interface GroceryViewItemActionDelegate {
    fun onTapDeleteButton(name: String)
    fun onTapEditGrocery(name: String, description: String, amount: Int)
    fun onTapFileUpload(grocery: GroceryVO)
}