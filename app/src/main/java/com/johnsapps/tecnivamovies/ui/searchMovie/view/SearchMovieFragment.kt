package com.johnsapps.tecnivamovies.ui.searchMovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.johnsapps.tecnivamovies.databinding.FragmentSearchMovieBinding
import com.johnsapps.tecnivamovies.ui.searchMovie.viewModel.SearchMovieViewModel
import com.johnsapps.tecnivamovies.ui.utils.RecyclerViewOnScroll
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovieFragment : Fragment() {
    private var _binding: FragmentSearchMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchMovieViewModel by viewModels()
    private lateinit var adapter: MovieItemUIAdapter
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
    }

    private fun initObservers() {
        viewModel.videoList.observe(viewLifecycleOwner) { list ->
            binding.run {
                isLoading = false
                isLastPage = viewModel.isLastPage
                if (list.isEmpty()) {
                    adapter.clearData()
                } else {
                    list?.let { adapter.setData(it.toMutableList()) }
                }
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }

    private fun initUI() {
        setSearchEditText()
        setUpRecyclerView()
    }

    private fun setSearchEditText() {
        binding.run {
            etDashboardFind.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        val queryToSearch = etDashboardFind.text.toString()
                        if (queryToSearch.isNotEmpty()) {
                            viewModel.searchMovie(queryToSearch)
                        }
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        adapter = MovieItemUIAdapter(
            arrayListOf(),
            onClick = { mediaVideo ->
                val title =
                    if (mediaVideo.title.isNullOrEmpty()) mediaVideo.name else mediaVideo.title
                findNavController()
                    .navigate(
                        SearchMovieFragmentDirections
                            .actionSearchMovieFragmentToDetailMovieFragment(
                                typeOfVideo = mediaVideo.mediaType,
                                idVideo = mediaVideo.id,
                                urlPoster = mediaVideo.getPosterImage(),
                                title = title.toString()
                            )
                    )
            })
        binding.rvListMovies.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvListMovies.adapter = adapter
        binding.rvListMovies.addOnScrollListener(object : RecyclerViewOnScroll() {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                viewModel.getListMovie()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}