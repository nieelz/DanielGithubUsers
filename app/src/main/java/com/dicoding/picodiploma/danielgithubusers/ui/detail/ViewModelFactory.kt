package com.dicoding.picodiploma.danielgithubusers.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.danielgithubusers.di.Injection
import com.dicoding.picodiploma.danielgithubusers.repository.FavoriteRepository
import com.dicoding.picodiploma.danielgithubusers.ui.favorite.ViewModelFavorite
import com.dicoding.picodiploma.danielgithubusers.ui.main.ViewModelActivity

class ViewModelFactory(var repository: FavoriteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ViewModelActivity::class.java) -> {
                ViewModelActivity(repository) as T
            }
            modelClass.isAssignableFrom(ViewModelDetail::class.java) -> {
                ViewModelDetail(repository) as T
            }
            modelClass.isAssignableFrom(ViewModelFavorite::class.java) -> {
                ViewModelFavorite(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }


    companion object {
        @Volatile
        var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideFavorite(context))
            }.also { instance = it }
    }


}

