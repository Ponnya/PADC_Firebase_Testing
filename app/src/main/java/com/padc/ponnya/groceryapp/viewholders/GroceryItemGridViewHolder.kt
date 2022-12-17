package com.padc.ponnya.groceryapp.viewholders

import com.bumptech.glide.Glide
import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.databinding.ViewHolderGroceryItemGridBinding
import com.padc.ponnya.groceryapp.delegates.GroceryViewItemActionDelegate

class GroceryItemGridViewHolder(
    private val mDelegate: GroceryViewItemActionDelegate,
    private val binding: ViewHolderGroceryItemGridBinding
) : BaseViewHolder<GroceryVO>(binding) {
    override fun bindData(data: GroceryVO) {
        binding.tvTitle.text = data.name
        binding.tvDescription.text = data.description
        binding.tvCount.text = "x ${data.amount.toString()}"

        binding.btnDelete.setOnClickListener {
            mDelegate.onTapDeleteButton(data.name ?: "")
        }

        binding.btnEdit.setOnClickListener {
            mDelegate.onTapEditGrocery(data.name ?: "", data.description ?: "", data.amount ?: 0)
        }

        binding.btnFileUpload.setOnClickListener {
            mDelegate.onTapFileUpload(data)
        }

        Glide.with(binding.root)
            .load(data.image)
            .into(binding.ivGroceryImage)
    }
}