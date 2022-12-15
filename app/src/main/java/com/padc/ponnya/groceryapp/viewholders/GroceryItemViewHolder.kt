package com.padc.ponnya.groceryapp.viewholders

import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.databinding.ViewHolderGroceryItemBinding

class GroceryItemViewHolder(private val binding: ViewHolderGroceryItemBinding) :
    BaseViewHolder<GroceryVO>(binding) {
    override fun bindData(data: GroceryVO) {
        binding.tvTitle.text = data.name
        binding.tvDescription.text = data.description
        binding.tvCount.text = "x ${data.amount.toString()}"
    }
}