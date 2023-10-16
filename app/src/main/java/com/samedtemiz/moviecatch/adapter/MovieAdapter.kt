package com.samedtemiz.moviecatch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ComputableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.samedtemiz.moviecatch.R
import com.samedtemiz.moviecatch.models.Result

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyCustomViewHolder>(){

    var liveData : List<Result>? = null

    fun setList(liveData: List<Result>){
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MyCustomViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val txt_MovieTitle = view.findViewById<TextView>(R.id.movie_title)
        fun bind(data: Result){
            txt_MovieTitle.text = data.title
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
        }else{
            return liveData!!.size
        }
    }
}