package com.share.appcompania.chucknorris.network

import com.share.appcompania.chucknorris.model.CategoriesModel
import com.share.appcompania.chucknorris.model.ChucknorrisModel
import com.share.appcompania.chucknorris.util.Constants.EndPoint.Companion.CATEGORIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServiceInterface {

    @GET(CATEGORIES)
    suspend fun getCategories() : Response<CategoriesModel>

    @GET
    suspend fun getRandomCategories(@Url endPoint: String): Response<ChucknorrisModel>

}