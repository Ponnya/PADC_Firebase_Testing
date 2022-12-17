package com.padc.ponnya.groceryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.databinding.ViewHolderGroceryItemGridBinding
import com.padc.ponnya.groceryapp.delegates.GroceryViewItemActionDelegate
import com.padc.ponnya.groceryapp.viewholders.GroceryItemGridViewHolder

class GroceryItemGridAdapter(private val mDelegate: GroceryViewItemActionDelegate) :
    BaseRecyclerAdapter<GroceryItemGridViewHolder, GroceryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryItemGridViewHolder {
        val view = ViewHolderGroceryItemGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GroceryItemGridViewHolder(mDelegate, view)
    }
}