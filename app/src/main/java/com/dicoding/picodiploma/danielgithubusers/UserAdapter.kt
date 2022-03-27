package com.dicoding.picodiploma.danielgithubusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.danielgithubusers.databinding.RowUserBinding

class UserAdapter(private val listUser: List<ItemsItem>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var binding: RowUserBinding

    override fun  onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder{
        binding = RowUserBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val user = listUser[position]
        with(binding){
            Glide.with(imgPhoto.context).load(user.avatarUrl).into(imgPhoto)
            tvUsername.text = user.login
        }
    }
    override fun getItemCount() = listUser.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}