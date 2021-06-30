package com.example.tpfinalmoviles.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tpfinalmoviles.data.model.Cow
import com.example.tpfinalmoviles.data.model.CowAlert
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.databinding.ActivityAddCowAlertBinding
import com.example.tpfinalmoviles.utils.Status

class AddCowAlertActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddCowAlertBinding
    private val viewModel = MainViewModel(AppRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCowAlertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
    }

    private fun setUpListeners() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.addAlertBtn.setOnClickListener {
            try {
                viewModel.addCowAlert(
                    CowAlert(
                        null,
                        binding.cowIdField.text.toString().toInt(),
                        null,
                        binding.bcsMaxField.text.toString().toDouble(),
                        binding.bcsMinField.text.toString().toDouble()
                    )
                )
            } catch (e: Exception) {
                Toast.makeText(this, "La alerta no pudo ser generada correctamente. Por favor verificar los datos ingresados.", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.addCowAlert.observe(this, {
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