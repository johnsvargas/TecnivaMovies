package com.johnsapps.tecnivamovies.ui.detailMovie.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnsapps.tecnivamovies.domain.model.map
import com.johnsapps.tecnivamovies.domain.useCase.GetDetailMovieUseCase
import com.johnsapps.tecnivamovies.ui.detailMovie.viewModel.uiModel.VideoUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val useCase: GetDetailMovieUseCase
) : ViewModel() {
    private val _detailVideo: MutableLiveData<VideoUI> = MutableLiveData()
    val detailVideo: LiveData<VideoUI> = _detailVideo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDetailVideo(id: Long, typeOfVide: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = useCase.getDetailMovie(id, typeOfVide)
                _detailVideo.postValue(response.map())
                _isLoading.value = false
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                _isLoading.value = false
            }

        }
    }
}