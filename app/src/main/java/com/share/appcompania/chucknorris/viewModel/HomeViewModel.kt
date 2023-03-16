package com.share.appcompania.chucknorris.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.share.appcompania.chucknorris.model.CategoriesModel
import com.share.appcompania.chucknorris.model.ChucknorrisModel
import com.share.appcompania.chucknorris.network.APIServiceInterface
import com.share.appcompania.chucknorris.network.RetrofitInstance
import com.share.appcompania.chucknorris.util.Constants.EndPoint.Companion.RANDOM_CATEGORIES
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _responseGetCategories = MutableLiveData<CategoriesModel?>()
    val responseGetCategories: LiveData<CategoriesModel?>
    get() = _responseGetCategories

    private var retrofit: APIServiceInterface? = null

    init {
        retrofit = RetrofitInstance.api
    }

    fun getCategories() {
        viewModelScope.launch {
            val call = retrofit?.getCategories()
            val response = call?.body()
            run {
                call?.let { result ->
                    if (result.isSuccessful) {
                        _responseGetCategories.postValue(response)
                    } else {
                        _responseGetCategories.postValue(null)
                    }
                }
            }
        }
    }


}