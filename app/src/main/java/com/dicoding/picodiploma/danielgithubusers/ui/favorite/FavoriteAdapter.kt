package com.dicoding.picodiploma.danielgithubusers.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.danielgithubusers.database.local.FavoriteEntity
import com.dicoding.picodiploma.danielgithubusers.databinding.RowFavoriteBinding


class FavoriteAdapter(private val listFavorite: List<FavoriteEntity>) :
    RecyclerView.Adapter<ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowFavoriteBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fav = listFavorite[position]
        with(holder) {
            with(listFavorite[position]) {
                Glide.with(binding.imgPhoto).load(avatarUrl).into(binding.imgPhoto)
                binding.tvUsername.text = fav.login

                holder.itemView.setOnClickListener {
                    listFavorite[position].login?.let { onItemClickCallback.onItemClicked(it) }
                }
            }
        }
    }

    override fun getItemCount() = listFavorite.size


    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }


}

class ViewHolder(val binding: RowFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
