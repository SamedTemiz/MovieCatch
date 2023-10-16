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
    var recentMovieList: MutableLiveData<Movie>

    init{
        popularMovieList = MutableLiveData()
        recentMovieList = MutableLiveData()
    }

    fun getObserverLiveData(isPopular: Boolean = false) : MutableLiveData<Movie>{
        if(isPopular){
            return popularMovieList
        }

        return recentMovieList
    }

    fun loadData(page: String, isPopular: Boolean){
        if(isPopular){
            repository.getPopularMovies(page, popularMovieList)
        }

        repository.getRecentMovies(page, recentMovieList)
    }

}