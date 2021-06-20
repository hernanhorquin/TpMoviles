package com.example.tpfinalmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tpfinalmoviles.data.model.Cow
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.databinding.ActivityAddCowBinding
import com.example.tpfinalmoviles.utils.Status

class AddCowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCowBinding
    private val viewModel = MainViewModel(AppRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
    }

    private fun setUpListeners() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.addCowBtn.setOnClickListener {
            viewModel.addCow(
                Cow(
                    null,
                    binding.totalPartosText.text.toString().toInt(),
                    binding.electronicIdText.text.toString().toInt(),
                    "2021-10-23",//binding.birthDayText.text.toString(),
                    binding.herdIdText.text.toString().toInt(),
                    binding.weigthText.text.toString().toDouble(),
                    "2021-10-23",//binding.lastBornDateText.text.toString(),
                    null
                )
            )
        }

        viewModel.createCow.observe(this, {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    Toast.makeText(this, "Id Vaca: " + it.data?.id, Toast.LENGTH_LONG).show()
                    this.finish()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Error: " + it.error?.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {

                }
            }
        })
    }
}