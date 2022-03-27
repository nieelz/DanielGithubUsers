package com.dicoding.picodiploma.danielgithubusers

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.danielgithubusers.databinding.FragmentFollowerBinding
import com.dicoding.picodiploma.danielgithubusers.databinding.FragmentFollowingBinding


class FollowerFragment : Fragment() {


    private lateinit var binding: FragmentFollowerBinding
    private lateinit var adapter: FollowAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?{
        binding = FragmentFollowerBinding.inflate(inflater,container,false)
        recycler = binding.rvFollower
        val viewmodel = ViewModelProvider(requireActivity()).get(ViewModelDetail::class.java)
        viewmodel.followerAccount().observe(viewLifecycleOwner){
            showFollowerData(it)
        }
        return binding.root

    }

    private fun showFollowerData(fol: ArrayList<FollowUserResponseItem>) {
        adapter = FollowAdapter(fol)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this@FollowerFragment.context)
        adapter.setOnItemClickCallback(object:FollowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: String) {
                val intent = Intent(this@FollowerFragment.context, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.USER_ID, data)
                startActivity(intent)
            }

        })
    }

}

