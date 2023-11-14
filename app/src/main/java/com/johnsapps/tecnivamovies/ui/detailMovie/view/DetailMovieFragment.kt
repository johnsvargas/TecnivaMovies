package com.johnsapps.tecnivamovies.ui.detailMovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.johnsapps.tecnivamovies.databinding.FragmentDetailMovieBinding
import com.johnsapps.tecnivamovies.ui.detailMovie.viewModel.DetailMovieViewModel
import com.johnsapps.tecnivamovies.ui.detailMovie.viewModel.uiModel.VideoUI
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
        viewModel.getDetailVideo(args.idVideo, args.typeOfVideo)
        initObservers()
        initUI(args.urlPoster, args.title)
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
        viewModel.detailVideo.observe(viewLifecycleOwner) {
            setDataVideoInView(it)
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

    private fun setDataVideoInView(videoUI: VideoUI) {
        binding.run {
            tvDetailOriginalTitle.text = videoUI.originalTitle
            tvDetailVoteAverage.text = videoUI.voteAverage
            tvDetailDescription.text = videoUI.description
            tvDetailGenres.text = videoUI.genres
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}