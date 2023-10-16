package com.samedtemiz.moviecatch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samedtemiz.moviecatch.di.retrofit.RetrofitRepository
import com.samedtemiz.moviecatch.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(private val repository: RetrofitRepository): ViewModel(){

    var popularMovieList: MutableLiveData<Movie>

    init{
        popularMovieList = MutableLiveData()
    }

    fun getObserverLiveData() : MutableLiveData<Movie>{
        return popularMovieList
    }

    fun loadPopularData(page: String){
        repository.getPopularMovies(page, popularMovieList)
    }

}