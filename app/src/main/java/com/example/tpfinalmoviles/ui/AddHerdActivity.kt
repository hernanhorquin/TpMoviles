package com.example.tpfinalmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tpfinalmoviles.data.model.Herd
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.databinding.ActivityAddHerdBinding
import com.example.tpfinalmoviles.utils.Status

class AddHerdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddHerdBinding
    private val viewModel = MainViewModel(AppRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddHerdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.addHerdBtn.setOnClickListener {
            viewModel.createHerd(
                Herd(null, binding.locationText.text.toString(), null, null)
            )
        }

        viewModel.createHerd.observe(this, {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    Toast.makeText(this, "Id Rodeo: " + it.data?.id, Toast.LENGTH_LONG).show()
                    this.finish()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Error " + it.error?.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {

                }
            }
        })
    }
}