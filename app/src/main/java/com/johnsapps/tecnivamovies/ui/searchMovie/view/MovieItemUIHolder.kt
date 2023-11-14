package com.johnsapps.tecnivamovies.ui.searchMovie.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.johnsapps.tecnivamovies.databinding.ItemCardMovieBinding
import com.johnsapps.tecnivamovies.ui.searchMovie.viewModel.uiModel.MediaVideoUI
import com.squareup.picasso.Picasso


class MovieItemUIHolder(view: View) : ViewHolder(view) {
    private val binding = ItemCardMovieBinding.bind(view)

    fun bind(item: MediaVideoUI, onClick: (MediaVideoUI) -> Unit) {
        binding.run {
            tvItemTitleMovie.text = item.name
            ivItemImageMovie
            Picasso
                .get()
                .load(item.posterUrl)
                .into(ivItemImageMovie)
            cvItemMovie.setOnClickListener { onClick(item) }
        }
    }
}