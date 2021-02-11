package com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters

import android.view.View
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.data.db.entities.Movie
import com.gcuello.chiper_movie.databinding.ItemMovieBinding
import com.xwray.groupie.viewbinding.BindableItem

class AdapterPopularMovieItem(val item: Movie) : BindableItem<ItemMovieBinding>() {
    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
        val pathImage = String.format(
            "%s%s",
            viewBinding.root.context.getString(R.string.url_image_display),
            item.posterPath
        )
        viewBinding.imageItem.setImageURI(pathImage)
        val average = item.voteAverage!! * 10
        viewBinding.userScore.text = "${average}%"
        if (item.voteAverage!! == 0.0){
            viewBinding.userScore.text = "NR"
        }

        if (item.adult!!){
            viewBinding.adultText.text = viewBinding.root.context.getString(R.string.string_content_adult)
        }
    }

    override fun getLayout(): Int = R.layout.item_movie

    override fun initializeViewBinding(view: View): ItemMovieBinding = ItemMovieBinding.bind(view)
}