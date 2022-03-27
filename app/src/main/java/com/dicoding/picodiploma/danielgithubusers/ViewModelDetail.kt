package com.dicoding.picodiploma.danielgithubusers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelDetail(data: String) : ViewModel() {


    private val _detailAccount = MutableLiveData<DetailUserResponse>()

    fun detailAccount(): LiveData<DetailUserResponse> {
        return _detailAccount
    }

    private val _followingAccount = MutableLiveData<ArrayList<FollowUserResponseItem>>()

    fun followingAccount() : LiveData<ArrayList<FollowUserResponseItem>>{
        return _followingAccount
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _followerAccount = MutableLiveData<ArrayList<FollowUserResponseItem>>()

    fun followerAccount() : LiveData<ArrayList<FollowUserResponseItem>>{
        return _followerAccount
    }

    init {
        setUserData(data)
        setFollowingData(data)
        setFollowersData(data)
    }

    fun setUserData(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetail(query)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            )
            {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailAccount.value = responseBody!!
                    }
                } else {
                    Log.e("RIP", "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("RIP", "onFailure: ${t.message}")
            }
        })
    }

    fun setFollowingData(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(query)
        client.enqueue(object : Callback<ArrayList<FollowUserResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<FollowUserResponseItem>>,
                response: Response<ArrayList<FollowUserResponseItem>>
            )
            {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _followingAccount.value = responseBody!!
                    }
                } else {
                    Log.e("RIP", "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ArrayList<FollowUserResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e("RIP", "onFailure: ${t.message}")
            }
        })
    }

    fun setFollowersData(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(query)
        client.enqueue(object : Callback<ArrayList<FollowUserResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<FollowUserResponseItem>>,
                response: Response<ArrayList<FollowUserResponseItem>>
            )
            {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _followerAccount.value = responseBody!!
                    }
                } else {
                    Log.e("RIP", "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ArrayList<FollowUserResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e("RIP", "onFailure: ${t.message}")
            }
        })
    }


}

class ViewModelFactory(var data: String):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return ViewModelDetail(data) as T
    }

}