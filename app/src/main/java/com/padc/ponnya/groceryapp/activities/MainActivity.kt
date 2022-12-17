package com.padc.ponnya.groceryapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padc.grocery.mvp.presenters.MainPresenter
import com.padc.grocery.mvp.presenters.impls.MainPresenterImpl
import com.padc.ponnya.groceryapp.R
import com.padc.ponnya.groceryapp.adapters.GroceryItemAdapter
import com.padc.ponnya.groceryapp.adapters.GroceryItemGridAdapter
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
    private lateinit var mGroceryItemAdapter: GroceryItemAdapter
    private lateinit var mGroceryItemGridAdapter: GroceryItemGridAdapter

    private lateinit var mPresenter: MainPresenter
    private var mVersion = 0

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        mPresenter = getPresenter<MainPresenterImpl, MainView>()

        setUpActionListeners()
        mPresenter.onUiReady(this, this)
        addCrashButton()
    }

    private fun addCrashButton() {
        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(
            crashButton, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
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

    override fun displayToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun showRecyclerView(version: Int) {
        mVersion = version
        if (version < 1) {
            mGroceryItemAdapter = GroceryItemAdapter(mPresenter)
            binding.rvGroceries.adapter = mGroceryItemAdapter
            binding.rvGroceries.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        } else {
            mGroceryItemGridAdapter = GroceryItemGridAdapter(mPresenter)
            binding.rvGroceries.adapter = mGroceryItemGridAdapter
            binding.rvGroceries.layoutManager =
                GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
        }
    }

    override fun showUserName(name: String) {
        binding.tvUserName.text = "Hello $name!"
    }


    override fun showGroceryData(groceryList: List<GroceryVO>) {
        if (mVersion < 1) {
            mGroceryItemAdapter.setNewData(groceryList)
        } else {
            mGroceryItemGridAdapter.setNewData(groceryList)
        }
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