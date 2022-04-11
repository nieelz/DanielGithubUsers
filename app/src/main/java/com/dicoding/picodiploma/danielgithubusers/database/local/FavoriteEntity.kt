package com.dicoding.picodiploma.danielgithubusers.database.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = null,
    @ColumnInfo(name = "login")
    var login: String? = null,
    @ColumnInfo(name = "bookmarked")
    var isBookmarked: Boolean = false
) : Parcelable


