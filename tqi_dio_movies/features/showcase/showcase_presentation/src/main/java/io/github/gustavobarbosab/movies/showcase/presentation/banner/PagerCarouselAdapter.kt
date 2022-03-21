package io.github.gustavobarbosab.movies.showcase.presentation.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.commons.ui.extension.loadImage
import io.github.gustavobarbosab.movies.showcase.databinding.ItemPagerCarouselBinding
import io.github.gustavobarbosab.movies.showcase.presentation.model.ShowCaseModel

class PagerCarouselAdapter(
    private val clickListener: (ShowCaseModel) -> Unit
) : RecyclerView.Adapter<PagerCarouselAdapter.ViewHolder>() {

    var items: List<ShowCaseModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemPagerCarouselBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        private val binding: ItemPagerCarouselBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: ShowCaseModel, clickListener: (ShowCaseModel) -> Unit) {
            binding.apply {
                root.setOnClickListener { clickListener(movie) }
                carouselImage.loadImage(movie.posterUrl)
                movieName.text = movie.name
            }
        }
    }
}