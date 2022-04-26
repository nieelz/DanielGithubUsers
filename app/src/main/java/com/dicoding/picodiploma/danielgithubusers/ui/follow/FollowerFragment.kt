package com.dicoding.picodiploma.danielgithubusers.ui.follow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.danielgithubusers.database.remote.response.FollowUserResponseItem
import com.dicoding.picodiploma.danielgithubusers.databinding.FragmentFollowerBinding
import com.dicoding.picodiploma.danielgithubusers.ui.detail.DetailUserActivity
import com.dicoding.picodiploma.danielgithubusers.ui.detail.FollowAdapter
import com.dicoding.picodiploma.danielgithubusers.ui.detail.ViewModelDetail

class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowerBinding
    private lateinit var adapter: FollowAdapter
    private lateinit var recycler: RecyclerView

    private val _isLoading = MutableLiveData<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        recycler = binding.rvFollower
        val viewModel = ViewModelProvider(requireActivity())[ViewModelDetail::class.java]
        viewModel.followerAccount().observe(viewLifecycleOwner) {
            showFollowerData(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        return binding.root

    }

    private fun showFollowerData(fol: ArrayList<FollowUserResponseItem>) {
        _isLoading.value = true
        adapter = FollowAdapter(fol)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this@FollowerFragment.context)
        adapter.setOnItemClickCallback(object : FollowAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                val intent = Intent(this@FollowerFragment.context, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.USER_ID, data)
                startActivity(intent)
                _isLoading.value = false
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}

