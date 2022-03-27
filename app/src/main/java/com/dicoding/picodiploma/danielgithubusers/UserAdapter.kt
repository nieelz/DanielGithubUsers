package com.dicoding.picodiploma.danielgithubusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.danielgithubusers.databinding.RowUserBinding

class UserAdapter(private val listUser: List<ItemsItem>) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun  onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder{
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
class ViewHolder(val binding: RowUserBinding) : RecyclerView.ViewHolder(binding.root)
