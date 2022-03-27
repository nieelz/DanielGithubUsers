package com.dicoding.picodiploma.danielgithubusers

import com.google.gson.annotations.SerializedName

data class FollowUserResponse(

	@field:SerializedName("FollowUserResponse")
	val followUserResponse: List<FollowUserResponseItem>
)

data class FollowUserResponseItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String
)
