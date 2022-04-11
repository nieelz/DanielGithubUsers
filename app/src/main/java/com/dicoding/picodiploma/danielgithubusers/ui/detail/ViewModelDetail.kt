package com.dicoding.picodiploma.danielgithubusers.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.danielgithubusers.database.remote.response.FollowUserResponseItem
import com.dicoding.picodiploma.danielgithubusers.database.local.FavoriteEntity
import com.dicoding.picodiploma.danielgithubusers.database.remote.response.DetailUserResponse
import com.dicoding.picodiploma.danielgithubusers.repository.FavoriteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelDetail(private val repository: FavoriteRepository) : ViewModel() {


    private val _detailAccount = MutableLiveData<DetailUserResponse>()
    fun detailAccount(): LiveData<DetailUserResponse> {
        return _detailAccount
    }

    private val _followingAccount = MutableLiveData<ArrayList<FollowUserResponseItem>>()
    fun followingAccount(): LiveData<ArrayList<FollowUserResponseItem>> {
        return _followingAccount
    }

    private val _followerAccount = MutableLiveData<ArrayList<FollowUserResponseItem>>()
    fun followerAccount(): LiveData<ArrayList<FollowUserResponseItem>> {
        return _followerAccount
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun setUserData(query: String) {
        _isLoading.value = true
        repository.getDetail(query).enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _detailAccount.value = response.body()
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
        repository.getFollowing(query)
            .enqueue(object : Callback<ArrayList<FollowUserResponseItem>> {
                override fun onResponse(
                    call: Call<ArrayList<FollowUserResponseItem>>,
                    response: Response<ArrayList<FollowUserResponseItem>>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        _followingAccount.value = response.body()
                    } else {
                        Log.e("RIP", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(
                    call: Call<ArrayList<FollowUserResponseItem>>,
                    t: Throwable
                ) {
                    _isLoading.value = false
                    Log.e("RIP", "onFailure: ${t.message}")
                }
            })
    }

    fun setFollowersData(query: String) {
        _isLoading.value = true
        repository.getFollowers(query)
            .enqueue(object : Callback<ArrayList<FollowUserResponseItem>> {
                override fun onResponse(
                    call: Call<ArrayList<FollowUserResponseItem>>,
                    response: Response<ArrayList<FollowUserResponseItem>>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        _followerAccount.value = response.body()
                    } else {
                        Log.e("RIP", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(
                    call: Call<ArrayList<FollowUserResponseItem>>,
                    t: Throwable
                ) {
                    _isLoading.value = false
                    Log.e("RIP", "onFailure: ${t.message}")
                }
            })
    }


    fun insert(favorite: FavoriteEntity) {
        repository.insert(favorite)
    }

    fun delete(favorite: FavoriteEntity) {
        repository.delete(favorite)
    }



}

