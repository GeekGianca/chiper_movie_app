package com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.core.PagedListGroup
import com.gcuello.chiper_movie.databinding.ItemGroupListBinding
import com.gcuello.chiper_movie.presentation.ui.detail.DetailActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class AdapterGroupMovieView(
    private val title: String,
    private val items: PagedListGroup<AdapterPopularMovieItem>
) : BindableItem<ItemGroupListBinding>() {

    private val groupieAdapter = GroupAdapter<GroupieViewHolder>()

    override fun bind(viewBinding: ItemGroupListBinding, position: Int) {
        groupieAdapter.add(items)
        viewBinding.title.text = title
        viewBinding.let {
            it.items.layoutManager =
                LinearLayoutManager(it.root.context, RecyclerView.HORIZONTAL, false)
            it.items.adapter = groupieAdapter
            groupieAdapter.setOnItemClickListener { item, _ ->
                when (item) {
                    is AdapterPopularMovieItem -> {
                        val intent = Intent(it.root.context, DetailActivity::class.java)
                        intent.putExtra("movie_id", item.id)
                        it.root.context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_group_list

    override fun initializeViewBinding(view: View): ItemGroupListBinding =
        ItemGroupListBinding.bind(view)
}