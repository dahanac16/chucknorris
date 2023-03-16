package com.share.appcompania.chucknorris.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.share.appcompania.chucknorris.model.ChucknorrisModel
import com.share.appcompania.chucknorris.network.APIServiceInterface
import com.share.appcompania.chucknorris.network.RetrofitInstance
import com.share.appcompania.chucknorris.util.Constants
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    private val _responseGetRandomCategories = MutableLiveData<ChucknorrisModel?>()
    val responseGetRandomCategories: LiveData<ChucknorrisModel?>
        get() = _responseGetRandomCategories

    private var retrofit: APIServiceInterface? = null

    init {
        retrofit = RetrofitInstance.api
    }

    fun getRandomCategories(category: String) {
        viewModelScope.launch {
            val call = retrofit?.getRandomCategories(Constants.EndPoint.RANDOM_CATEGORIES + category)
            val response = call?.body()
            run {
                call?.let { result ->
                    if (result.isSuccessful) {
                        _responseGetRandomCategories.postValue(response)
                    } else {
                        _responseGetRandomCategories.postValue(null)
                    }
                }
            }
        }
    }
}