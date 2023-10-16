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
import org.w3c.dom.Text

class RecentMovieAdapter(private val isFirstScreen: Boolean = true) : RecyclerView.Adapter<RecentMovieAdapter.MyCustomViewHolder>(){

    var liveData : List<Result>? = null

    fun setList(liveData: List<Result>){
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MyCustomViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val txt_MovieTitle = view.findViewById<TextView>(R.id.movie_title)
        val txt_MovieGenre = view.findViewById<TextView>(R.id.movie_genre)
        val img_MoviePoster = view.findViewById<ImageView>(R.id.movie_poster)
        val txt_MovieReleaseDate = view.findViewById<TextView>(R.id.movie_releaseDate)
        val txt_MovieVoteAvarage = view.findViewById<TextView>(R.id.movie_voteAverage)

        fun bind(data: Result){
            txt_MovieTitle.text = data.title
            txt_MovieGenre.text = "Aksiyon, Bilim Kurgu, Komedi"
            Glide.with(img_MoviePoster)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(img_MoviePoster)
            txt_MovieReleaseDate.text = data.release_date
            txt_MovieVoteAvarage.text = data.vote_average.toString() + "/ 10"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentMovieAdapter.MyCustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_movie_item, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentMovieAdapter.MyCustomViewHolder, position: Int) {
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