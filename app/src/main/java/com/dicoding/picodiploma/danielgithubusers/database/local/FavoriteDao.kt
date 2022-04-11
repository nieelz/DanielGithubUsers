package com.dicoding.picodiploma.danielgithubusers.database.local

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteEntity: FavoriteEntity)

    @Update
    fun update(favoriteEntity: FavoriteEntity)

    @Delete
    fun delete(favoriteEntity: FavoriteEntity)

    @Query("SELECT * from favoriteentity ORDER BY id ASC")
    fun getAlldetail(): LiveData<List<FavoriteEntity>>

    @Query("SELECT * FROM favoriteentity WHERE login")
    fun checkData(): LiveData<List<FavoriteEntity>>
}