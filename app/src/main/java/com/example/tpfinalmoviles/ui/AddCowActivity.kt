package com.example.tpfinalmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tpfinalmoviles.databinding.ActivityAddCowBinding

class AddCowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}