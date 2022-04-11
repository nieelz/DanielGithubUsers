package com.dicoding.picodiploma.danielgithubusers.database.remote.response

import com.google.gson.annotations.SerializedName

data class FollowUserResponseItem(

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("login")
    val login: String
)
