package com.samedtemiz.moviecatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.samedtemiz.moviecatch.adapter.MovieAdapter
import com.samedtemiz.moviecatch.models.Movie
import com.samedtemiz.moviecatch.viewmodel.HomepageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(HomepageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_movie)
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter

        viewModel.getObserverLiveData().observe(this, object: Observer<Movie>{
            override fun onChanged(value: Movie) {
                if(value != null){
                    movieAdapter.setList(value.results)
                }
            }

        })

        viewModel.loadPopularData("1")
    }
}