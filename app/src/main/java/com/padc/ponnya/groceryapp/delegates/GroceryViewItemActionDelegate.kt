package com.padc.ponnya.groceryapp.delegates

interface GroceryViewItemActionDelegate {
    fun onTapDeleteButton(name: String)
    fun onTapEditGrocery(name: String, description: String, amount: Int)
}