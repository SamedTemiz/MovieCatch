package com.samedtemiz.moviecatch.ui.fragments.home.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samedtemiz.moviecatch.R
import com.samedtemiz.moviecatch.adapter.MovieAdapter
import com.samedtemiz.moviecatch.adapter.RecentMovieAdapter
import com.samedtemiz.moviecatch.databinding.FragmentHomeBinding
import com.samedtemiz.moviecatch.models.Movie
import com.samedtemiz.moviecatch.viewmodel.HomepageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recentMovieAdapter: RecentMovieAdapter

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(HomepageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecyclerView()
        viewModel.getObserverLiveData(true).observe(this, object : Observer<Movie> {
            override fun onChanged(value: Movie) {
                movieAdapter.setList(value.results)
            }

        })

        viewModel.getObserverLiveData(false).observe(this, object : Observer<Movie> {
            override fun onChanged(value: Movie) {
                recentMovieAdapter.setList(value.results)
            }

        })

        fetchMovies()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerView() {
        val lmHorizontal =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val lmVertical =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val recycler_recentMovie = findViewById<RecyclerView>(R.id.recycler_recentMovie)
        val recycler_populerMovie = findViewById<RecyclerView>(R.id.recycler_popularMovie)

        recycler_populerMovie.layoutManager = lmHorizontal
        recycler_recentMovie.layoutManager = lmVertical

        movieAdapter = MovieAdapter()
        recentMovieAdapter = RecentMovieAdapter()
        recycler_populerMovie.adapter = movieAdapter
        recycler_recentMovie.adapter = recentMovieAdapter
    }

    fun fetchMovies() {

        CoroutineScope(Dispatchers.IO).launch {

            val job1: Deferred<Unit> = async {
                viewModel.loadData("1", true)
            }

            val job2: Deferred<Unit> = async {
                viewModel.loadData("1", false)
            }

            job1.await()
            job2.await()
        }

    }

}