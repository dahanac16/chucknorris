package com.share.appcompania.chucknorris.network

import com.share.appcompania.chucknorris.util.Constants.Api.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: APIServiceInterface by lazy {
        retrofit().create(APIServiceInterface::class.java)
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}