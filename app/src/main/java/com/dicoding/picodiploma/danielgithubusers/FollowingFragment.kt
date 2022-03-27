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

import com.dicoding.picodiploma.danielgithubusers.databinding.FragmentFollowingBinding


class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding
    private lateinit var adapter: FollowAdapter
    private lateinit var recycler: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentFollowingBinding.inflate(inflater,container,false)
        recycler = binding.rvFollowing
        val viewmodel = ViewModelProvider(requireActivity()).get(ViewModelDetail::class.java)
        viewmodel.followingAccount().observe(viewLifecycleOwner){
            showFollowingData(it)
        }
        return binding.root

    }

    private fun showFollowingData(fol: ArrayList<FollowUserResponseItem>) {
        adapter = FollowAdapter(fol)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this@FollowingFragment.context)
        adapter.setOnItemClickCallback(object:FollowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: String) {
                val intent = Intent(this@FollowingFragment.context, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.USER_ID, data)
                startActivity(intent)
            }

        })
    }


}