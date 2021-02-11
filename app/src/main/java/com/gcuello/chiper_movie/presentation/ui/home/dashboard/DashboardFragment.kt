package com.gcuello.chiper_movie.presentation.ui.home.dashboard

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcuello.chiper_movie.ChiperMovieApp
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.core.PagedListGroup
import com.gcuello.chiper_movie.databinding.FragmentDashboardBinding
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterGroupMovieView
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterPopularMovieItem
import com.gcuello.chiper_movie.presentation.viewModel.DashboardViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import javax.inject.Inject
import kotlin.random.Random

class DashboardFragment : Fragment() {
    companion object {
        private const val TAG = "DashboardFragment"
    }

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val adapter = GroupAdapter<GroupieViewHolder>()
    private val popularMovieList = mutableListOf<Item<*>>()
    private val pagedPopularMovies = PagedListGroup<AdapterPopularMovieItem>()
    private lateinit var adapterMovies: AdapterGroupMovieView

    @Inject
    lateinit var viewModel: DashboardViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ChiperMovieApp).movieComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.let {
            it.multipleList.layoutManager =
                LinearLayoutManager(it.root.context, RecyclerView.VERTICAL, false)
            it.multipleList.adapter = adapter
        }
        adapterMovies =
            AdapterGroupMovieView(getString(R.string.string_popular_movies), pagedPopularMovies)
        popularMovieList.add(adapterMovies)
        adapter.addAll(popularMovieList)
        adapter.addAll(popularMovieList)
        adapter.addAll(popularMovieList)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.observerPopularMoviePagedList.observe(viewLifecycleOwner, {
            Log.d(TAG, "${it.size}")
            pagedPopularMovies.submitList(it)
            val random = Random.nextInt(0, it.size)
            val item = it[random]
            if (item == null) {
                bindInfoRandom(it[0])
            } else {
                bindInfoRandom(item)
            }
        })
        viewModel.observerGenresNames.observe(viewLifecycleOwner, {
            binding.genre.text = it.joinToString(separator = " • ")
        })
    }

    private fun bindInfoRandom(item: AdapterPopularMovieItem?) {
        val pathImage = String.format(
            "%s%s",
            getString(R.string.url_image_display),
            item!!.item.posterPath
        )
        binding.backgroundHomeImage.setImageURI(pathImage)
        binding.homeImage.setImageURI(pathImage)
        val removeLast = item.item.genreIds!!.substring(0,item.item.genreIds!!.length - 1)
        Log.d(TAG, removeLast)
        viewModel.findGenresHome(listOf(*removeLast.replace(" ", "").split(",").toTypedArray()))
    }
}