package com.padc.ponnya.groceryapp.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padc.grocery.mvp.presenters.MainPresenter
import com.padc.grocery.mvp.presenters.impls.MainPresenterImpl
import com.padc.ponnya.groceryapp.R
import com.padc.ponnya.groceryapp.adapters.GroceryItemAdapter
import com.padc.ponnya.groceryapp.data.vos.GroceryVO
import com.padc.ponnya.groceryapp.databinding.ActivityMainBinding
import com.padc.ponnya.groceryapp.dialogs.GroceryDialogFragment
import com.padc.ponnya.groceryapp.mvp.views.MainView

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

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG)
    }
}