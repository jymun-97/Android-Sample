package com.sample.viewpager2_sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.sample.viewpager2_sample.R
import com.sample.viewpager2_sample.databinding.ActivityMainBinding
import com.sample.viewpager2_sample.ui.adapter.ContactAdapter
import com.sample.viewpager2_sample.ui.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val contactViewModel by viewModels<ContactViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewModel = contactViewModel
            lifecycleOwner = this@MainActivity

            viewPager.adapter = ContactAdapter(
                onItemClicked = {
                    Toast.makeText(
                        this@MainActivity,
                        "${it.name} : ${it.phone}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
}