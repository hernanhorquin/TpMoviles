package com.example.tpfinalmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tpfinalmoviles.databinding.ActivityAddHerdBinding

class AddHerdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddHerdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddHerdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}