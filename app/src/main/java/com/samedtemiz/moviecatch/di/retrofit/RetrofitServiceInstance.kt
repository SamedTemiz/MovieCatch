package com.samedtemiz.moviecatch.di.retrofit


import com.samedtemiz.moviecatch.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {

    @GET("3/movie/popular?api_key=1a94d694f3eeb233d257efab00fd7051")
    fun getPopularVideos(@Query("page") query: String): Call<Movie>

    @GET("3/movie/now_playing?api_key=1a94d694f3eeb233d257efab00fd7051")
    fun getRecentVideos(@Query("page") query: String) : Call<Movie>

    @GET("3/genre/movie/list?api_key=1a94d694f3eeb233d257efab00fd7051")
    fun getGenres(): Call<Genre>

    @GET("3/movie/{id}/vid eos?api_key=1a94d694f3eeb233d257efab00fd7051")
    fun getTrailerTeasers(@Path("id") id: Int): Call<Trailer>

    @GET("3/search/movie?api_key=1a94d694f3eeb233d257efab00fd7051&language=en_US")
    fun getSuggestions(@Query("query") query: String): Call<Movie>

}