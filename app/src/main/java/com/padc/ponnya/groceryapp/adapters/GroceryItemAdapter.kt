package com.padc.ponnya.groceryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.databinding.ViewHolderGroceryItemBinding
import com.padc.ponnya.groceryapp.viewholders.GroceryItemViewHolder

class GroceryItemAdapter : BaseRecyclerAdapter<GroceryItemViewHolder, GroceryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryItemViewHolder {
        val view =
            ViewHolderGroceryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroceryItemViewHolder(view)
    }

}

