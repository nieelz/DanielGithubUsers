package com.dicoding.picodiploma.danielgithubusers.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.danielgithubusers.ItemsItem
import com.dicoding.picodiploma.danielgithubusers.UserResponse
import com.dicoding.picodiploma.danielgithubusers.repository.FavoriteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelActivity(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private val _account = MutableLiveData<List<ItemsItem>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun account(): LiveData<List<ItemsItem>> {
        return _account
    }


    fun setUserData(query: String) {
        _isLoading.value = true

        favoriteRepository.getUser(query).enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _account.value = response.body()?.items
                } else {
                    Log.e("RIP", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("RIP", "onFailure: ${t.message}")
            }
        })
    }
}