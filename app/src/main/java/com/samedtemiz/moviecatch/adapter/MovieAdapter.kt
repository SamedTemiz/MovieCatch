package com.samedtemiz.moviecatch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samedtemiz.moviecatch.R
import com.samedtemiz.moviecatch.models.Result

class MovieAdapter(private val isFirstScreen: Boolean = true) : RecyclerView.Adapter<MovieAdapter.MyCustomViewHolder>(){

    var liveData : List<Result>? = null

    fun setList(liveData: List<Result>){
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MyCustomViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val txt_MovieTitle = view.findViewById<TextView>(R.id.movie_title)
        val txt_MovieGenre = view.findViewById<TextView>(R.id.movie_genre)
        val img_MoviePoster = view.findViewById<ImageView>(R.id.movie_poster)

        fun bind(data: Result){
            txt_MovieTitle.text = data.title
            txt_MovieGenre.text = "Aksiyon, Bilim Kurgu, Komedi"
            Glide.with(img_MoviePoster)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(img_MoviePoster)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MyCustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_item, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyCustomViewHolder, position: Int) {
        holder.bind(liveData!!.get(position))
    }

    override fun getItemCount(): Int {
        if(liveData == null){
            return 0
        }else if(isFirstScreen){
            return 10
        }else{
            return liveData!!.size
        }
    }
}