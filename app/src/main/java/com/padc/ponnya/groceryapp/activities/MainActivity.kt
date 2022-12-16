package com.padc.ponnya.groceryapp.activities

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padc.grocery.mvp.presenters.MainPresenter
import com.padc.grocery.mvp.presenters.impls.MainPresenterImpl
import com.padc.ponnya.groceryapp.R
import com.padc.ponnya.groceryapp.adapters.GroceryItemAdapter
import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.databinding.ActivityMainBinding
import com.padc.ponnya.groceryapp.dialogs.GroceryDialogFragment
import com.padc.ponnya.groceryapp.dialogs.GroceryDialogFragment.Companion.BUNDLE_AMOUNT
import com.padc.ponnya.groceryapp.dialogs.GroceryDialogFragment.Companion.BUNDLE_DESCRIPTION
import com.padc.ponnya.groceryapp.dialogs.GroceryDialogFragment.Companion.BUNDLE_NAME
import com.padc.ponnya.groceryapp.mvp.views.MainView
import java.io.IOException

class MainActivity : AbstractBaseActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: GroceryItemAdapter

    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mPresenter = getPresenter<MainPresenterImpl, MainView>()

        setUpRecyclerView()
        setUpActionListeners()
        mPresenter.onUiReady(this)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null || data?.data != null) {
                    val filePath = data.data
                    try {
                        filePath?.let {
                            if (Build.VERSION.SDK_INT >= 29) {
                                val source: ImageDecoder.Source =
                                    ImageDecoder.createSource(this.contentResolver, it)

                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mPresenter.onPhotoTaken(bitmap)
                            } else {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    this.contentResolver,
                                    filePath
                                )
                                mPresenter.onPhotoTaken(bitmap)
                            }
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

    private fun setUpActionListeners() {
        binding.fab.setOnClickListener {
            GroceryDialogFragment.newFragment()
                .show(supportFragmentManager, GroceryDialogFragment.TAG_ADD_GROCERY_DIALOG)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = GroceryItemAdapter(mPresenter)
        binding.rvGroceries.adapter = mAdapter
        binding.rvGroceries.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun showGroceryData(groceryList: List<GroceryVO>) {
        mAdapter.setNewData(groceryList)
    }

    override fun showGroceryDialog(name: String, description: String, amount: String) {
        val groceryDialog = GroceryDialogFragment.newFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_NAME, name)
        bundle.putString(BUNDLE_DESCRIPTION, description)
        bundle.putString(BUNDLE_AMOUNT, amount)
        groceryDialog.arguments = bundle
        groceryDialog.show(supportFragmentManager, GroceryDialogFragment.TAG_ADD_GROCERY_DIALOG)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG)
    }

    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        resultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }
}