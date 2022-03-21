package io.github.gustavobarbosab.movies.favorite.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.commons.ui.extension.loadImage
import io.github.gustavobarbosab.movies.favorite.model.FavoriteModel
import io.github.gustavobarbosab.movies.favorite.presentation.FavoritesMoviesRecyclerAdapter.ViewHolder
import io.github.gustavobarbosab.movies.favorite.R
import io.github.gustavobarbosab.movies.favorite.databinding.ItemFavoritesMoviesBinding

class FavoritesMoviesRecyclerAdapter(
    val listener: (Int, FavoriteModel) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    var list: MutableList<FavoriteModel> = mutableListOf()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size)
        }

    fun removeMovie(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoritesMoviesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(
        private val binding: ItemFavoritesMoviesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FavoriteModel, position: Int) = with(binding) {
            binding.movieLike.setOnClickListener { listener(position, model) }
            movieImage.loadImage(model.imageUrl)
            movieName.text = model.movieName
            val context = movieSavedDate.context
            movieSavedDate.text = context.getString(
                R.string.favorite_movie_saved_date,
                model.savedDate
            )
        }
    }
}