package com.example.tpfinalmoviles.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tpfinalmoviles.R
import com.example.tpfinalmoviles.data.model.CowFiredAlert
import com.example.tpfinalmoviles.data.model.Herd
import com.example.tpfinalmoviles.data.model.HerdFiredAlert
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.databinding.ActivityGetHerdBinding
import com.example.tpfinalmoviles.utils.Status

class GetHerdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetHerdBinding

    private val viewModel = MainViewModel(AppRepository())

    private var currentHerd: Herd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetHerdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
    }

    private fun setUpListeners() {

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.searchButton.setOnClickListener {
            viewModel.getHerd(binding.searchInput.text.toString().toInt())
            viewModel.getHerdAlerts()
        }

        viewModel.getHerd.observe(this, {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    setUI(it.data)
                    currentHerd = it.data
                }
                Status.ERROR -> {
                    Toast.makeText(this, "No se pudo obtener el rodeo buscado", Toast.LENGTH_LONG)
                        .show()
                }
                Status.LOADING -> {

                }
            }
        })

        viewModel.getHerdAlerts.observe(this, {
            when (it.responseType) {
                Status.SUCCESSFUL -> {

                    var lastHerdFiredAlert: HerdFiredAlert? = null
                    it.data.let {
                        it?.forEach { herdAlert ->
                            if (herdAlert.herd.id == currentHerd?.id) {
                                if (lastHerdFiredAlert == null) {
                                    lastHerdFiredAlert = herdAlert
                                } else {
                                    if (herdAlert.fecha > lastHerdFiredAlert!!.fecha) {
                                        lastHerdFiredAlert = herdAlert
                                    }
                                }
                            }
                        }
                    }

                    if (lastHerdFiredAlert?.bcsFired ?: 0.0 == currentHerd?.bcsPromedio) {
                        binding.alertTextField.text = "CC fuera de los valores esperados"
                        binding.alertTextField.setTextColor(Color.parseColor("#b71c1c"))
                        binding.alertIcon.setImageResource(R.drawable.warningsign)
                    } else {
                        binding.alertTextField.text = "CC dentro de los valores esperados"
                        binding.alertTextField.setTextColor(Color.parseColor("#1b5e20"))
                        binding.alertIcon.setImageResource(R.drawable.ticksign)
                    }

                    binding.alertCardView.visibility = View.VISIBLE

                }
                Status.ERROR -> {
                    Toast.makeText(this, "No se pudo obtener el rodeo buscado", Toast.LENGTH_LONG)
                        .show()
                }
                Status.LOADING -> {

                }
            }
        })
    }

    private fun getLastAlert(data: List<HerdFiredAlert>?): HerdFiredAlert? {
        data?.let { list ->
            if (list.isNotEmpty()) {
                val lastAlert: List<HerdFiredAlert> = list.filter {
                    it.id == currentHerd?.id
                }.sortedByDescending {
                    it.fecha
                }
                return if (lastAlert.isNotEmpty())
                    lastAlert[0]
                else
                    null
            } else null
        }
        return null
    }

    private fun setUI(data: Herd?) {
        binding.locationField.text = "Ubicacion: " + data?.location
        binding.cowIdField.text = "ID: " + data?.id
        binding.ccField.text = "CC: " + data?.bcsPromedio
    }
}
