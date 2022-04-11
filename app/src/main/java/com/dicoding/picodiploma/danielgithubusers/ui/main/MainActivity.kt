package com.dicoding.picodiploma.danielgithubusers.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.danielgithubusers.ItemsItem
import com.dicoding.picodiploma.danielgithubusers.R
import com.dicoding.picodiploma.danielgithubusers.databinding.ActivityMainBinding
import com.dicoding.picodiploma.danielgithubusers.ui.detail.DetailUserActivity
import com.dicoding.picodiploma.danielgithubusers.ui.detail.ViewModelFactory
import com.dicoding.picodiploma.danielgithubusers.ui.favorite.FavoriteActivity
import com.dicoding.picodiploma.danielgithubusers.ui.setting.SettingPreferences
import com.dicoding.picodiploma.danielgithubusers.ui.setting.SettingsActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var recycler: RecyclerView
    private val viewModelActivity: ViewModelActivity by viewModels {
        ViewModelFactory.getInstance(
            this
        )
    }

    private var darkMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Github Users"
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            recycler = binding.rvUser

        getThemeApp()
        setThemeApp(darkMode)


        viewModelActivity.account().observe(this) {
            showUserData(it)
        }

        viewModelActivity.isLoading.observe(this) {
            showLoading(it)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModelActivity.setUserData(query)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                true
            }
            R.id.menu2 -> {
                val i = Intent(this, SettingsActivity::class.java)
                startActivity(i)
                true
            }
            else -> true
        }
    }


    private fun showUserData(userList: List<ItemsItem>) {
        val list = ArrayList<ItemsItem>()
        list.addAll(userList)

        adapter = UserAdapter(userList)


        recycler.adapter = adapter
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.USER_ID, data)
                startActivity(intent)
            }
        })
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    private fun getThemeApp() {
        lifecycleScope.launch {
            SettingPreferences.getThemes(this@MainActivity).collect {
                darkMode = it
                setThemeApp(it)
            }
        }
    }


    private fun setThemeApp(isDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(if (isDarkMode) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES)
    }


}
