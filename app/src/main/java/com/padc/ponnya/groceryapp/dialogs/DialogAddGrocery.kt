package com.padc.ponnya.groceryapp.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.padc.ponnya.groceryapp.databinding.DialogAddGroceryBinding

class GroceryDialogFragment : DialogFragment() {
    private lateinit var binding: DialogAddGroceryBinding

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
        binding.btnAddGrocery.setOnClickListener {
            dismiss()
        }
    }
}