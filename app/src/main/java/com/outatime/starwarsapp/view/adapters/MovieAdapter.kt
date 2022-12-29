package com.outatime.starwarsapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outatime.starwarsapp.databinding.ActivityMovieDetailsBinding
import com.outatime.starwarsapp.model.Movie
import com.outatime.starwarsapp.model.MovieDetail
import com.outatime.starwarsapp.util.Constants
import com.outatime.starwarsapp.view.activities.RVMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieAdapter(private val movieContext: Context, private val movie: Movie): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(view: ActivityMovieDetailsBinding): RecyclerView.ViewHolder(view.root) {
        val tvName = view.tvMovieName
        val tvEpisodeId = view.tvEpisodeId
        val tvDirector = view.tvDirector
        val tvDate = view.tvReleaseDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val movieAdapterBinding = ActivityMovieDetailsBinding.inflate(LayoutInflater.from(movieContext))
        return ViewHolder(movieAdapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvEpisodeId.text = movie.results[position].episode_id.toString()
        holder.tvName.text = movie.results[position].title
        holder.tvDirector.text = movie.results[position].director
        holder.tvDate.text = movie.results[position].release_date

        holder.itemView.setOnClickListener {
            if(movieContext is RVMovie) {
                movieContext.selectedMovie(movie, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return movie.results.size
    }

}