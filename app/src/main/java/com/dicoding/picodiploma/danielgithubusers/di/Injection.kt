package com.dicoding.picodiploma.danielgithubusers.di

import android.content.Context
import com.dicoding.picodiploma.danielgithubusers.database.local.FavoriteRoomDatabase
import com.dicoding.picodiploma.danielgithubusers.database.remote.retrofit.ApiConfig
import com.dicoding.picodiploma.danielgithubusers.repository.FavoriteRepository

object Injection {
    fun provideFavorite(context: Context): FavoriteRepository {
        val serviceAPI = ApiConfig.getApiService()
        val database = FavoriteRoomDatabase.getDatabase(context)
        val dao = database.favoriteDao()
        return FavoriteRepository(serviceAPI, dao)
    }
}