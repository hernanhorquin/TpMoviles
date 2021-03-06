package com.example.tpfinalmoviles.ui

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.tpfinalmoviles.R
import com.example.tpfinalmoviles.data.model.Cow
import com.example.tpfinalmoviles.data.model.CowFiredAlert
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.databinding.ActivityGetCowBinding
import com.example.tpfinalmoviles.utils.Status
import java.lang.Exception

class GetCowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetCowBinding
    private val viewModel = MainViewModel(AppRepository())

    private var currentCow: Cow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetCowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.searchButton.setOnClickListener {
            hideKeyboard()
            binding.searchInput.clearFocus()

            val searchInputText: String = binding.searchInput.text.toString()

            val idToSearch = searchInputText.toInt()
            viewModel.getCow(idToSearch)
            viewModel.getCowAlerts()

        }

        viewModel.getCow.observe(this, { dataCow ->
            when (dataCow.responseType) {
                Status.SUCCESSFUL -> {
                    dataCow.data.let {
                        setUiValues(it!!)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "No se pudo obtener la vaca buscada", Toast.LENGTH_LONG)
                        .show()
                }
                Status.LOADING -> {

                }
            }
        })

        viewModel.getCowAlerts.observe(this, { cowFiredAlerts ->
            when (cowFiredAlerts.responseType) {
                Status.SUCCESSFUL -> {

                    var lastCowFiredAlert: CowFiredAlert? = null
                    cowFiredAlerts.data.let {
                        it?.forEach { cowAlert ->
                            if (cowAlert.cow.id == currentCow?.id) {
                                if (lastCowFiredAlert == null) {
                                    lastCowFiredAlert = cowAlert
                                } else {
                                    if (cowAlert.fecha > lastCowFiredAlert!!.fecha) {
                                        lastCowFiredAlert = cowAlert
                                    }
                                }
                            }
                        }
                    }

                    if (lastCowFiredAlert?.bcsFired ?: 0.0 == currentCow?.cc) {
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
                    Toast.makeText(this, "No se pudo obtener la vaca buscada", Toast.LENGTH_LONG)
                        .show()
                }
                Status.LOADING -> {

                }
            }
        })

    }

    private fun getLastAlert(data: List<CowFiredAlert>?): CowFiredAlert? {
        data?.let { list ->
            if (list.isNotEmpty()) {
                val lastAlert: List<CowFiredAlert> = list.filter {
                    it.id == currentCow?.id
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


    private fun setUiValues(cow: Cow) {
        currentCow = cow
        binding.cowIdField.setText("ID: " + cow!!.id)
        binding.ccField.setText("CC: " + cow.cc)
        binding.electronicIdField.setText("ID electr??nico: " + cow.electronicId)
        binding.cantidadPartosField.setText("Cantidad de partos: " + cow.cantidadPartos)
        binding.fechaNacimientoField.setText(
            "Fecha de nacimiento: " + cow.fechaNacimiento.substring(
                0,
                10
            )
        )
        binding.herdIdField.setText("Pertenece al rodeo: " + cow.herdId)
        binding.pesoField.setText("Peso: " + cow.peso)

        val fechaUltimoParto: String
        if (cow.ultimaFechaParto != null) {
            fechaUltimoParto = cow.ultimaFechaParto.substring(0, 10)
        } else {
            fechaUltimoParto = "N/A"
        }
        binding.fechaUltimoPartoField.setText("Fecha de ??ltimo parto: " + fechaUltimoParto)
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken, 0)
        }

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}