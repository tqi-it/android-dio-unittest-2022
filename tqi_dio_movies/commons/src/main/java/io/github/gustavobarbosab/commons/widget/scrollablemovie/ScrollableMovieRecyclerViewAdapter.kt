package io.github.gustavobarbosab.commons.widget.scrollablemovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.commons.databinding.ItemScrollableMoviesBinding

typealias ScrollableBinding = ItemScrollableMoviesBinding.(Int) -> Unit

abstract class ScrollableMovieRecyclerViewAdapter :
    RecyclerView.Adapter<ScrollableMovieRecyclerViewAdapter.ViewHolder>() {

    abstract val bindingViewHolder: ScrollableBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemScrollableMoviesBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindingViewHolder(holder.binding, position)
    }

    class ViewHolder(val binding: ItemScrollableMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)
}