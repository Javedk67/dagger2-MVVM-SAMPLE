package com.example.dagger2sample.api


import com.example.dagger2sample.data.model.ListResponse

import com.example.dagger2sample.utils.Config
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST(Config.BASE_URL)
    suspend fun getList(
        @Query("apikey") apikey: String,
        @Query("s") s: String,
        @Query("type") type: String,
        @Query("page") page: Int
    ): ListResponse
}