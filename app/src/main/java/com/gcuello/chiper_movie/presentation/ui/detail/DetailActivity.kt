package com.gcuello.chiper_movie.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}