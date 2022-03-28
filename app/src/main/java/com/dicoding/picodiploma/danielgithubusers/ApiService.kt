package com.dicoding.picodiploma.danielgithubusers

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") username: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") following: String
    ): Call <ArrayList<FollowUserResponseItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") followers: String
    ): Call <ArrayList<FollowUserResponseItem>>


}


