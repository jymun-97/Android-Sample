package com.sample.recyclerview_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sample.recyclerview_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberViewModel = ViewModelProvider(this)[NumberViewModel::class.java]
        binding.apply {
            viewModel = numberViewModel
            lifecycleOwner = this@MainActivity
        }
    }
}