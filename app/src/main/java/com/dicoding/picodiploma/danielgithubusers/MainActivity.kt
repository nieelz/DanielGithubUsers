package com.dicoding.picodiploma.danielgithubusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.danielgithubusers.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var recycler: RecyclerView

    companion object {
        private const val TAG = "MainActivity"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recycler = binding.rvUser
        supportActionBar?.hide()
        val viewmodel = ViewModelProvider(this).get(ViewModelActivity::class.java)
        viewmodel.account().observe(this){
            showUserData(it)
        }

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

        val layoutManager = LinearLayoutManager(this@MainActivity)
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
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }



}






//    private fun setUserData() {
//        showLoading(true)
//        val client = ApiConfig.getApiService().getUser("sidiqpermana")
//        client.enqueue(object : Callback<UserResponse> {
//            override fun onResponse(
//                call: Call<UserResponse>,
//                response: Response<UserResponse>
//            )
//            {
//                showLoading(false)
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        showUserData(responseBody.items)
//                    }
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                showLoading(false)
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }