package com.dicoding.picodiploma.danielgithubusers.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.danielgithubusers.R
import com.dicoding.picodiploma.danielgithubusers.database.local.FavoriteEntity
import com.dicoding.picodiploma.danielgithubusers.databinding.ActivityFavoriteBinding
import com.dicoding.picodiploma.danielgithubusers.ui.detail.DetailUserActivity
import com.dicoding.picodiploma.danielgithubusers.ui.detail.ViewModelFactory
import com.dicoding.picodiploma.danielgithubusers.ui.setting.SettingsActivity


class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter
    private lateinit var recycler: RecyclerView


    private val viewModelFavorite: ViewModelFavorite by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recycler = binding.rvFavorite

        viewModelFavorite.getAllFavorite().observe(this) {
            showFavoriteData(it)
        }
    }

    private fun showFavoriteData(favList: List<FavoriteEntity>) {
        val list = ArrayList<FavoriteEntity>()
        list.addAll(favList)

        adapter = FavoriteAdapter(favList)

        recycler.adapter = adapter
        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                val intent = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.USER_ID, data)
                startActivity(intent)
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


}