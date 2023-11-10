package com.johnsapps.tecnivamovies.ui.searchMovie.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.johnsapps.tecnivamovies.data.model.MediaType
import com.johnsapps.tecnivamovies.data.model.MediaVideo
import com.johnsapps.tecnivamovies.databinding.ItemCardMovieBinding
import com.squareup.picasso.Picasso


class MovieItemUIHolder(view: View): ViewHolder(view) {
    val binding = ItemCardMovieBinding.bind(view)

    fun bind(item: MediaVideo, onClick:(MediaVideo)-> Unit ) {
        binding.run {
            tvItemTitleMovie.text = when(item.mediaType){
                MediaType.Movie.value -> item.originalTitle
                MediaType.Tv.value -> item.name
                else -> item.name
            }
            ivItemImageMovie
            Picasso
                .get()
                .load(item.getPosterImage())
                .into(ivItemImageMovie)
            cvItemMovie.setOnClickListener { onClick(item) }
        }
    }
}