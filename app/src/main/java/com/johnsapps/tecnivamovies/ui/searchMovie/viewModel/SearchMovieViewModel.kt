package com.johnsapps.tecnivamovies.ui.searchMovie.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnsapps.tecnivamovies.domain.model.map
import com.johnsapps.tecnivamovies.domain.useCase.GetSearchMovieUseCase
import com.johnsapps.tecnivamovies.ui.searchMovie.viewModel.uiModel.MediaVideoUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val getSearchMovieUseCase: GetSearchMovieUseCase
) : ViewModel() {
    private val _videoList: MutableLiveData<List<MediaVideoUI>> = MutableLiveData()
    val videoList: LiveData<List<MediaVideoUI>> = _videoList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var numPage = 1
    private var _queryToSearch = ""
    var isLastPage = false

    fun setUp() {

    }

    fun searchMovie(queryToSearch: String) {
        _queryToSearch = queryToSearch
        numPage = 1
        _videoList.value = emptyList()
        getListMovie()
    }

    fun getListMovie() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = getSearchMovieUseCase.invoke(_queryToSearch, numPage)
                checkLastPage(response.totalPages)
                if (response.listMovie.isEmpty() && response.page.toInt() == 0) {
                    //showMessage("No se encontraron resultados _queryToSearch")
                } else {
                    _videoList.postValue(response.listMovie.map {
                        it.map()
                    })
                }
                _isLoading.value = false
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                _isLoading.value = false
            }
        }
    }

    private fun checkLastPage(totalPages: Long) {
        if (numPage <= totalPages) {
            numPage += 1
        } else {
            isLastPage = true
        }
    }
}