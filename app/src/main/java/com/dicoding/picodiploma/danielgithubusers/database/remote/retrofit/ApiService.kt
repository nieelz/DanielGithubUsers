package com.dicoding.picodiploma.danielgithubusers.database.remote.retrofit

import com.dicoding.picodiploma.danielgithubusers.BuildConfig
import com.dicoding.picodiploma.danielgithubusers.database.remote.response.FollowUserResponseItem
import com.dicoding.picodiploma.danielgithubusers.UserResponse
import com.dicoding.picodiploma.danielgithubusers.database.remote.response.DetailUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUser(
        @Query("q") username: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getFollowers(
        @Path("username") following: String
    ): Call<ArrayList<FollowUserResponseItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getFollowing(
        @Path("username") followers: String
    ): Call<ArrayList<FollowUserResponseItem>>

}


