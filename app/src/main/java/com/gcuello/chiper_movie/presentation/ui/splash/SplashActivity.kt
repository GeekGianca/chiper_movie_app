package com.gcuello.chiper_movie.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.gcuello.chiper_movie.ChiperMovieApp
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.core.SharedPreferencesManager
import com.gcuello.chiper_movie.databinding.ActivitySplashBinding
import com.gcuello.chiper_movie.presentation.ui.home.HomeActivity
import com.gcuello.chiper_movie.presentation.viewModel.SplashViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {
    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val componentDagger = (applicationContext as ChiperMovieApp).movieComponent
        componentDagger.inject(this)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        attachObservables()
    }

    private fun attachObservables() {
        viewModel.observableSaveSuccess.observe(this, {
            if (it == "movie") {
                viewModel.refreshTvGenresLocal()
            } else {
                goHomeActivity()
                SharedPreferencesManager.isRefresh(this, true)
            }
        })

        viewModel.observerErrorRefresh().observe(this, {
            val snackBar = Snackbar.make(
                binding.root,
                getString(R.string.string_failed_loading_genres),
                Snackbar.LENGTH_INDEFINITE
            )
            snackBar.setBackgroundTint(ContextCompat.getColor(this, R.color.white))
            snackBar.setTextColor(ContextCompat.getColor(this, R.color.black))
            snackBar.show()
            if (SharedPreferencesManager.isRefresh(this)) {
                snackBar.dismiss()
                goHomeActivity()
            }
        })
    }

    private fun goHomeActivity() {
        val extras = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            binding.imageView,
            "image_toolbar"
        )
        val home = Intent(this, HomeActivity::class.java)
        startActivity(home, extras.toBundle())
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshMoviesGenresLocal()
    }
}