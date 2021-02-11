package com.gcuello.chiper_movie.presentation.ui.detail.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.data.db.entities.Cast
import com.gcuello.chiper_movie.databinding.ItemCastingViewBinding

class AdapterCastItem(private val items: List<Cast>, private val ctx: Context) :
    RecyclerView.Adapter<AdapterCastItem.CastViewHolder>() {
    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCastingViewBinding.bind(itemView)

        fun bind(item: Cast) {
            val pathImage = String.format(
                "%s%s",
                ctx.getString(R.string.url_image_display),
                item.profilePath
            )
            binding.let {
                it.cast.setImageURI(pathImage)
                it.actor.text = item.character
                it.originalName.text = item.originalName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_casting_view, parent, false))

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}