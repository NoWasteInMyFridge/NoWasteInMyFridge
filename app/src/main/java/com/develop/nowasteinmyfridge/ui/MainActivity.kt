package com.develop.nowasteinmyfridge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}