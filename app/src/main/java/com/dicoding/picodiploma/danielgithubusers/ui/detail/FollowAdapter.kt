package com.dicoding.picodiploma.danielgithubusers.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.danielgithubusers.database.remote.response.FollowUserResponseItem
import com.dicoding.picodiploma.danielgithubusers.ui.main.ViewHolder
import com.dicoding.picodiploma.danielgithubusers.databinding.RowUserBinding

class FollowAdapter(private val listUser: ArrayList<FollowUserResponseItem>) : RecyclerView.Adapter<ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun  onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowUserBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val user = listUser[position]
        with(viewHolder){
            with(listUser[position]){
                Glide.with(binding.imgPhoto).load(avatarUrl).into(binding.imgPhoto)
                binding.tvUsername.text = user.login

                viewHolder.itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(listUser[viewHolder.adapterPosition].login)
                }
            }
        }
    }

    override fun getItemCount() = listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }


}