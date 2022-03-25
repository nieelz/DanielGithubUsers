package com.dicoding.picodiploma.danielgithubusers

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users?q={username}")
    fun getUser(
        @Path("username") username: String
    ): Call<UserResponse>
}


