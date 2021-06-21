package com.example.tpfinalmoviles.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tpfinalmoviles.data.model.CowAlert
import com.example.tpfinalmoviles.data.model.HerdAlert
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.databinding.ActivityAddHerdAlertBinding
import com.example.tpfinalmoviles.utils.Status

class AddHerdAlertActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddHerdAlertBinding
    private val viewModel = MainViewModel(AppRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddHerdAlertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
    }

    private fun setUpListeners() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.addAlertBtn.setOnClickListener {
            viewModel.addHerdAlert(
                HerdAlert(
                    null,
                    binding.herdIdField.text.toString().toInt(),
                    binding.bcsMaxField.text.toString().toDouble(),
                    binding.bcsMinField.text.toString().toDouble()
                )
            )
        }

        viewModel.addHerdAlert.observe(this, {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    Toast.makeText(this, "Nueva alerta generada con id: " + it.data?.id, Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "La alerta no pudo ser generada correctamente", Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {

                }
            }
        })

    }

}