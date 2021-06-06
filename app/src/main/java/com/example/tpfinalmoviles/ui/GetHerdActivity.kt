package com.example.tpfinalmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tpfinalmoviles.databinding.ActivityGetHerdBinding

class GetHerdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetHerdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetHerdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}