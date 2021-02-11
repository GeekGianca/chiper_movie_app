package com.gcuello.chiper_movie.presentation.ui.home.comingsoon

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcuello.chiper_movie.ChiperMovieApp
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.core.PagedListGroup
import com.gcuello.chiper_movie.databinding.FragmentComingSoonBinding
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterGroupMovieView
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterPopularMovieItem
import com.gcuello.chiper_movie.presentation.viewModel.ComingSoonVViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import javax.inject.Inject

class ComingSoonFragment : Fragment() {
    private var _binding: FragmentComingSoonBinding? = null
    private val binding get() = _binding!!
    private val comingSoonList = mutableListOf<Item<*>>()
    private val comingSoonMovies = PagedListGroup<AdapterPopularMovieItem>()
    private lateinit var adapterComingSoonMovies: AdapterGroupMovieView
    private val adapter = GroupAdapter<GroupieViewHolder>()

    @Inject
    lateinit var viewModel: ComingSoonVViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ChiperMovieApp).movieComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComingSoonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.observableComingSoon.observe(viewLifecycleOwner, {
            comingSoonMovies.submitList(it)
        })
    }

    override fun onResume() {
        super.onResume()
        if (resources.getBoolean(R.bool.is_landscape)) {
            binding.comingSoonList.apply {
                setHasFixedSize(true)
                layoutManager =
                    GridLayoutManager(requireContext(), 4, RecyclerView.VERTICAL, false)
            }
        } else {
            binding.comingSoonList.apply {
                setHasFixedSize(true)
                layoutManager =
                    GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            }
        }
        adapterComingSoonMovies =
            AdapterGroupMovieView(getString(R.string.string_coming_soon_movies), comingSoonMovies)
        comingSoonList.add(adapterComingSoonMovies)
        adapter.addAll(comingSoonList)
    }

    override fun onStart() {
        super.onStart()
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.string_title_coming_soon)
            .setMessage(R.string.string_coming_soon_message)
            .setPositiveButton(R.string.string_accept) { dialog, _ ->
                dialog.dismiss()
            }.create()
            .show()
    }

}