package com.dicoding.picodiploma.danielgithubusers

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("username") username: String
    ): Call<UserResponse>
}


