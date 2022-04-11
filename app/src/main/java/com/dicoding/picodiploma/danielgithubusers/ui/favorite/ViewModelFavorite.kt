package com.dicoding.picodiploma.danielgithubusers.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.danielgithubusers.database.local.FavoriteEntity
import com.dicoding.picodiploma.danielgithubusers.repository.FavoriteRepository

class ViewModelFavorite(private val repository: FavoriteRepository) : ViewModel() {
    fun getAllFavorite(): LiveData<List<FavoriteEntity>> = repository.getAllNotes()

}
