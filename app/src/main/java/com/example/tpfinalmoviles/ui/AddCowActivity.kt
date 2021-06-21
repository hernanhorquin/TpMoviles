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

        //val cow = Cow(null, 2, 3, "2018-10-09", 1, 5.3, null)
        binding.addCowBtn.setOnClickListener {

            var totalPartos: Int? = null
            var electronicId: Int? = null
            var weight: Double? = null
            var herdId: Int? = null

            if (binding.totalPartosText.text.toString().isNotEmpty()) {
                totalPartos = binding.totalPartosText.text.toString().toInt()
            }

            if (binding.electronicIdText.text.toString().isNotEmpty()) {
                electronicId = binding.electronicIdText.text.toString().toInt()
            }

            if (binding.weigthText.text.toString().isNotEmpty()) {
                weight = binding.weigthText.text.toString().toDouble()
            }

            if (binding.herdIdText.text.toString().isNotEmpty()) {
                herdId = binding.herdIdText.text.toString().toInt()
            }

            viewModel.addCow(
                Cow(
                    null,
                    totalPartos!!,
                    electronicId!!,
                    binding.birthDayText.text.toString(),
                    herdId!!,
                    weight!!,
                    binding.lastBornDateText.text.toString(),
                    null
                )
            )
        }

        viewModel.createCow.observe(this, {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    Toast.makeText(this, "Vaca cargada exitosamente con id: " + it.data?.id, Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "La vaca no pudo ser generada correctamente", Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {

                }
            }
        })
    }
}