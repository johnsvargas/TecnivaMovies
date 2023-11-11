package com.johnsapps.tecnivamovies.ui.detailMovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnsapps.tecnivamovies.data.model.MovieNow
import com.johnsapps.tecnivamovies.data.model.TvSeriesNow
import com.johnsapps.tecnivamovies.domain.useCase.GetDetailMovieUseCase
import com.johnsapps.tecnivamovies.ui.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val useCase: GetDetailMovieUseCase
):ViewModel() {
    private val _detailMovie: MutableLiveData<MovieNow> = MutableLiveData()
    val detailMovie: LiveData<MovieNow> = _detailMovie

    private val _detailTvSeries: MutableLiveData<TvSeriesNow> = MutableLiveData()
    val detailTvSeries: LiveData<TvSeriesNow> = _detailTvSeries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private var _typeOfVideo: String = ""
    private var _idVideo: Long = 0

    fun setBundle(typeVideo:String, idVideo: Long) {
        _typeOfVideo =  typeVideo
        _idVideo = idVideo
        getData()
    }

    private fun getData(){
        when(_typeOfVideo){
            Constants.TYPE_MOVIE -> { getDetailMovie(_idVideo)  }
            Constants.TYPE_TV_SERIES -> { getDetailTvSeries(_idVideo) }
        }
    }
    private fun getDetailMovie(id:Long){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = useCase.getDetailMovie(id)
                _detailMovie.postValue(response)
                _isLoading.value = false
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                _isLoading.value = false
            }

        }
    }

    private fun getDetailTvSeries(id:Long){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = useCase.getDetailTvSeries(id)
                _detailTvSeries.postValue(response)
                _isLoading.value = false
            } catch(e: Exception) {
                Log.e("Error", e.message.toString())
                _isLoading.value = false
            }

        }

    }
}