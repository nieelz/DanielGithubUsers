package com.dicoding.picodiploma.danielgithubusers

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("q") username: String
    ): Call<UserResponse>

    @GET("users/{username}")
    fun getDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowing(
        @Path("username") following: String
    ): Call <ArrayList<FollowUserResponseItem>>

    @GET("users/{username}/following")
    fun getFollowers(
        @Path("username") followers: String
    ): Call <ArrayList<FollowUserResponseItem>>


}


