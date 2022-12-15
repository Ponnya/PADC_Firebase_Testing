package com.padc.ponnya.groceryapp.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.padc.grocery.mvp.presenters.MainPresenter
import com.padc.grocery.mvp.presenters.impls.MainPresenterImpl
import com.padc.ponnya.groceryapp.databinding.DialogAddGroceryBinding

class GroceryDialogFragment : DialogFragment() {
    private lateinit var binding: DialogAddGroceryBinding

    private lateinit var mPresenter: MainPresenter

    companion object {
        const val TAG_ADD_GROCERY_DIALOG = "TAG_ADD_GROCERY_DIALOG"

        fun newFragment(): GroceryDialogFragment {
            return GroceryDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAddGroceryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()

        binding.btnAddGrocery.setOnClickListener {
            mPresenter.onTapAddGrocery(
                binding.etGroceryName.text.toString(),
                binding.etDescription.text.toString(),
                binding.etAmount.text.toString().toInt()
            )
            dismiss()
        }
    }

    private fun setUpPresenter() {
        activity?.let {
            mPresenter = ViewModelProvider(it)[MainPresenterImpl::class.java]
        }
    }
}