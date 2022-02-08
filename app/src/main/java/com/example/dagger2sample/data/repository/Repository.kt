package com.example.dagger2sample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.dagger2sample.api.ApiService

import com.example.dagger2sample.data.datasource.ListDataSource

import com.example.dagger2sample.utils.LOAD_SIZE
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    fun getImagesList() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = LOAD_SIZE),
        pagingSourceFactory = {
            ListDataSource(apiService)
        }
    ).flow

}