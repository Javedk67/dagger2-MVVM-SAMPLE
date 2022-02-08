package com.example.dagger2sample.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn

import com.example.dagger2sample.data.model.MovieBean
import com.example.dagger2sample.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var currentResult1: Flow<PagingData<MovieBean>>? = null

    fun getImages1(): Flow<PagingData<MovieBean>> {
        val newResult: Flow<PagingData<MovieBean>> =
            repository.getImagesList().cachedIn(viewModelScope)
        currentResult1 = newResult
        return newResult
    }

}
