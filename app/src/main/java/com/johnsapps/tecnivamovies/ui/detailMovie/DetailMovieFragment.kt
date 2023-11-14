package com.johnsapps.tecnivamovies.ui.detailMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.johnsapps.tecnivamovies.data.model.MovieNow
import com.johnsapps.tecnivamovies.data.model.TvSeriesNow
import com.johnsapps.tecnivamovies.databinding.FragmentDetailMovieBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailMovieViewModel by viewModels()
    private val args: DetailMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setBundle(args.typeOfVideo, args.idVideo)
        initObservers()
        initUI(args.urlPoster, args.title)
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
        viewModel.detailMovie.observe(viewLifecycleOwner) {
            setDataMovieInView(it)
        }

        viewModel.detailTvSeries.observe(viewLifecycleOwner) {
            setDataTvSerieInView(it)
        }
    }

    private fun initUI(urlPoster: String, title: String) {
        binding.run {
            toolbar.title = title
            tvDetailOriginalTitle.text = title
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
        setPosterImage(urlPoster)
    }

    private fun setPosterImage(imageUrl: String) {
        Picasso
            .get()
            .load(imageUrl)
            .into(binding.ivDetailAppBarImage)
    }

    private fun setDataMovieInView(movie: MovieNow) {
        binding.run {
            tvDetailOriginalTitle.text = movie.originalTitle
            tvDetailVoteAverage.text = movie.voteAverage.toString()
            tvDetailDescription.text = movie.overview
            var genresText = ""
            movie.genres.forEach {
                genresText += if (genresText.isEmpty()) it.name else String.format(", ${it.name}")
            }
            tvDetailGenres.text = genresText
        }
    }

    private fun setDataTvSerieInView(serie: TvSeriesNow) {
        binding.run {
            tvDetailOriginalTitle.text = serie.originalName
            tvDetailVoteAverage.text = serie.voteAverage.toString()
            tvDetailDescription.text = serie.overview
            var genresText = ""
            serie.genres.forEach {
                genresText += if (genresText.isEmpty()) it.name else String.format(", ${it.name}")
            }
            tvDetailGenres.text = genresText
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}