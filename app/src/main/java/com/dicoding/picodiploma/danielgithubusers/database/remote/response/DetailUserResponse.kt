package com.dicoding.picodiploma.danielgithubusers.database.remote.response

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(

    @field:SerializedName("hireable")
    val hireable: Any,

    @field:SerializedName("followers")
    val followers: Int,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("following")
    val following: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("bio")
    val bio: Any,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("public_repos")
    val publicRepos: Int,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("email")
    val email: Any,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("userBookmark")
    val isBookmarkedUser: Boolean
)
