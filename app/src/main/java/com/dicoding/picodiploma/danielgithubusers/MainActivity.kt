package com.dicoding.picodiploma.danielgithubusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.danielgithubusers.databinding.ActivityMainBinding
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Github Users"
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recycler = binding.rvUser
        val viewmodel = ViewModelProvider(this).get(ViewModelActivity::class.java)
        viewmodel.account().observe(this){
            showUserData(it)
        }
        viewmodel.isLoading.observe(this, {
            showLoading(it)
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                        viewmodel.setUserData(query)
                    return false
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }
            })
    }

    private fun showUserData(userList: List<ItemsItem>) {
        val list = ArrayList<ItemsItem>()
        list.addAll(userList)

        adapter = UserAdapter(userList)
        recycler.adapter = adapter
        adapter.setOnItemClickCallback(object:UserAdapter.OnItemClickCallback{
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

}
