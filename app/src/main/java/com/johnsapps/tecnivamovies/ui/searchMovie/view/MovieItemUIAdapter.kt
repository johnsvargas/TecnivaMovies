package com.johnsapps.tecnivamovies.ui.searchMovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johnsapps.tecnivamovies.R
import com.johnsapps.tecnivamovies.ui.searchMovie.viewModel.uiModel.MediaVideoUI

class MovieItemUIAdapter(
    private var movieList: MutableList<MediaVideoUI>,
    private val onClick: (MediaVideoUI) -> Unit
) :
    RecyclerView.Adapter<MovieItemUIHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemUIHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieItemUIHolder(layoutInflater.inflate(R.layout.item_card_movie, parent, false))
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieItemUIHolder, position: Int) {
        val movieUI = movieList[position]
        holder.bind(movieUI, onClick)
    }

    fun setData(data: MutableList<MediaVideoUI>) {
        val size = movieList.size
        movieList.addAll(data)
        val sizeNew = movieList.size
        notifyItemRangeChanged(size, sizeNew)
    }

    fun clearData() {
        movieList.clear()
        notifyItemRangeChanged(0, itemCount)
    }

}