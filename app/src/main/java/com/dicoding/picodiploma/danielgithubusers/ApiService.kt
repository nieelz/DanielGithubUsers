package com.dicoding.picodiploma.danielgithubusers

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token ghp_123blabla123")
    fun getUser(
        @Query("q") username: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ghp_123blabla123")
    fun getDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ghp_123blabla123")
    fun getFollowing(
        @Path("username") following: String
    ): Call <ArrayList<FollowUserResponseItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ghp_123blabla123")
    fun getFollowers(
        @Path("username") followers: String
    ): Call <ArrayList<FollowUserResponseItem>>


    //pindahkan ke build.gradle


}


