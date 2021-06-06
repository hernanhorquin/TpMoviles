package com.example.tpfinalmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tpfinalmoviles.databinding.ActivityGetCowBinding

class GetCowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetCowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetCowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}