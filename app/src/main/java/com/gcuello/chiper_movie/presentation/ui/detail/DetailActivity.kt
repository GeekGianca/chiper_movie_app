package com.gcuello.chiper_movie.presentation.ui.detail

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcuello.chiper_movie.ChiperMovieApp
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.databinding.ActivityDetailBinding
import com.gcuello.chiper_movie.presentation.ui.detail.adapters.AdapterCastItem
import com.gcuello.chiper_movie.presentation.viewModel.DetailViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "DetailActivity"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var _adapter: AdapterCastItem
    private var homePage: String = ""

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (applicationContext as ChiperMovieApp).movieComponent.inject(this)
        initView()
        attachObservables()
    }

    private fun initView() {
        binding.back.setOnClickListener {
            finish()
        }

        binding.goHomePage.setOnClickListener {
            if (homePage.isNotBlank()) {
                startWebIntent(homePage)
            } else {
                Toast.makeText(this, R.string.url_not_found, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun startWebIntent(url: String) {
        val builder = CustomTabsIntent.Builder()
        builder.setStartAnimations(
            this,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        );
        builder.setExitAnimations(
            this,
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        );
        builder.setDefaultColorSchemeParams(
            CustomTabColorSchemeParams
                .Builder()
                .setToolbarColor(ContextCompat.getColor(this, R.color.black))
                .setNavigationBarColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorBlackTransparent
                    )
                )
                .build()
        )
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    private fun attachObservables() {
        viewModel.observerErrorNetwork.observe(this, {
            val snackBar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackBar.setBackgroundTint(ContextCompat.getColor(this, R.color.white))
            snackBar.setTextColor(ContextCompat.getColor(this, R.color.black))
            snackBar.show()
        })

        viewModel.observableMovieDetail.observe(this, {
            if (it != null) {
                val pathImage =
                    String.format("%s%s", getString(R.string.url_image_display), it.posterPath)
                val backdropImage =
                    String.format("%s%s", getString(R.string.url_image_display), it.backdropPath)
                binding.backDetailImage.setImageURI(backdropImage)
                binding.frontDetailImage.setImageURI(pathImage)
                binding.votes.text =
                    String.format("%s%s", getString(R.string.string_votes), it.voteCount)
                homePage = it.homepage!!
                binding.overview.text = it.overview
                binding.title.text = it.title
                val average = it.voteAverage!! * 10
                if (it.voteAverage == 0.0) {
                    binding.chipVote.setChipBackgroundColorResource(R.color.black)
                    binding.chipVote.text = "NR"
                } else {
                    binding.chipVote.text = "$average%"
                    if (average > 0 && average < 50) {
                        binding.chipVote.setChipBackgroundColorResource(R.color.low)
                    } else if (average > 50 && average < 70) {
                        binding.chipVote.setChipBackgroundColorResource(R.color.medium)
                    } else {
                        binding.chipVote.setChipBackgroundColorResource(R.color.u_high)
                    }
                }
                if (it.genres!!.isNotEmpty()) {
                    viewModel.fetchGenres(it.genres!!)
                }
            }
        })

        viewModel.observableGenres.observe(this, {
            binding.genres.text = it.joinToString(separator = " • ")
        })

        viewModel.observableCastingDetail.observe(this, {
            if (!it.isNullOrEmpty()) {
                _adapter = AdapterCastItem(it, this@DetailActivity)
                binding.castList.adapter = _adapter
                _adapter.notifyDataSetChanged()
            }
        })

        viewModel.observableErrorSaveDetail.observe(this, {
            val snackBar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackBar.setBackgroundTint(ContextCompat.getColor(this, R.color.white))
            snackBar.setTextColor(ContextCompat.getColor(this, R.color.black))
            snackBar.show()
        })
    }

    override fun onResume() {
        super.onResume()
        if(resources.getBoolean(R.bool.is_landscape)){
            binding.castList.apply {
                setHasFixedSize(true)
                layoutManager =
                    GridLayoutManager(this@DetailActivity, 4, RecyclerView.VERTICAL, false)
            }
        }else{
            binding.castList.apply {
                setHasFixedSize(true)
                layoutManager =
                    GridLayoutManager(this@DetailActivity, 2, RecyclerView.VERTICAL, false)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (intent.extras != null) {
            val movieId = intent.extras!!.getInt("movie_id")
            viewModel.fetchDetailMovieFromNetworkToLocal(movieId)
        } else {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.string_title_empty_movie)
                .setMessage(R.string.string_message_empty_movie)
                .setPositiveButton(
                    R.string.string_accept
                ) { dialog, _ ->
                    dialog.dismiss()
                    finish()
                }.create().show()
        }
    }
}