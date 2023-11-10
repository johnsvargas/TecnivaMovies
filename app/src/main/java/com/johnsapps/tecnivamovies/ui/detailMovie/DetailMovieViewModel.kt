package com.johnsapps.tecnivamovies.ui.detailMovie

import android.os.Bundle
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
    private val _urlPoster: MutableLiveData<String> = MutableLiveData()
    val urlPoster: LiveData<String> =  _urlPoster
    private var _idVideo: Long = 0
    private val _title: MutableLiveData<String> = MutableLiveData()
    val title: LiveData<String> =  _title
    fun setBundle(bundle: Bundle) {
        _typeOfVideo =  bundle.getString(Constants.TYPE_OF_VIDEO)?:""
        _urlPoster.value = bundle.getString(Constants.URL_POSTER,"")
        _idVideo = bundle.getLong(Constants.ID_VIDEO,0)
        _title.value = bundle.getString(Constants.TITLE,"")
        getData()
    }

    private fun getData(){
        when(_typeOfVideo){
            Constants.TYPE_MOVIE -> { getDetailMovie(_idVideo)  }
            Constants.TYPE_TV_SERIES -> { getDetailTvSeries(_idVideo) }
        }
    }
    private fun getDetailMovie(id:Long){
        viewModelScope.launch {
            val response = useCase.getDetailMovie(id)
            _detailMovie.postValue(response)
        }
    }

    private fun getDetailTvSeries(id:Long){
        viewModelScope.launch {
            val response = useCase.getDetailTvSeries(id)
            _detailTvSeries.postValue(response)
        }

    }
}