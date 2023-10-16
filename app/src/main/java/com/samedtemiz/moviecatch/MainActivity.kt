package com.samedtemiz.moviecatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samedtemiz.moviecatch.adapter.MovieAdapter
import com.samedtemiz.moviecatch.adapter.RecentMovieAdapter
import com.samedtemiz.moviecatch.models.Movie
import com.samedtemiz.moviecatch.viewmodel.HomepageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recentMovieAdapter: RecentMovieAdapter

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(HomepageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initRecyclerView()
        viewModel.getObserverLiveData(true).observe(this, object: Observer<Movie>{
            override fun onChanged(value: Movie) {
                if(value != null){
                    movieAdapter.setList(value.results)
                }
            }

        })

        viewModel.getObserverLiveData(false).observe(this, object: Observer<Movie>{
            override fun onChanged(value: Movie) {
                if(value != null){
                    recentMovieAdapter.setList(value.results)
                }
            }

        })

        fetchMovies()
    }

    fun initRecyclerView(){
        val lmHorizontal = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        val lmVertical = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        val recycler_recentMovie = findViewById<RecyclerView>(R.id.recycler_recentMovie)
        val recycler_populerMovie = findViewById<RecyclerView>(R.id.recycler_popularMovie)

        recycler_populerMovie.layoutManager = lmHorizontal
        recycler_recentMovie.layoutManager = lmVertical

        movieAdapter = MovieAdapter()
        recentMovieAdapter = RecentMovieAdapter()
        recycler_populerMovie.adapter = movieAdapter
        recycler_recentMovie.adapter = recentMovieAdapter
    }

    fun fetchMovies(){

        CoroutineScope(Dispatchers.IO).launch {

            val job1 : Deferred<Unit> = async {
                viewModel.loadData("1", true)
            }

            val job2 : Deferred<Unit> = async {
                viewModel.loadData("1", false)
            }

            job1.await()
            job2.await()
        }

    }
}