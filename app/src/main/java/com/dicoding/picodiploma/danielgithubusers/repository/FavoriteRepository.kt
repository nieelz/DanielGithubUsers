package com.dicoding.picodiploma.danielgithubusers.repository

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.danielgithubusers.database.local.FavoriteDao
import com.dicoding.picodiploma.danielgithubusers.database.local.FavoriteEntity
import com.dicoding.picodiploma.danielgithubusers.database.remote.retrofit.ApiService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(
    private val serviceAPI: ApiService,
    private val dao: FavoriteDao
) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getAllNotes(): LiveData<List<FavoriteEntity>> = dao.getAlldetail()

    fun checkData(): LiveData<List<FavoriteEntity>> = dao.getAlldetail()

    fun insert(favoriteEntity: FavoriteEntity) {
        executorService.execute { dao.insert(favoriteEntity) }
    }

    fun delete(favoriteEntity: FavoriteEntity) {
        executorService.execute { dao.delete(favoriteEntity) }
    }

    fun update(favoriteEntity: FavoriteEntity) {
        executorService.execute { dao.update(favoriteEntity) }
    }

    fun getUser(username: String) = serviceAPI.getUser(username)
    fun getDetail(username: String) = serviceAPI.getDetail(username)
    fun getFollowers(followers: String) = serviceAPI.getFollowers(followers)
    fun getFollowing(following: String) = serviceAPI.getFollowing(following)


}