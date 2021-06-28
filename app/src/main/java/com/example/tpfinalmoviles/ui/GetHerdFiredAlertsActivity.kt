package com.example.tpfinalmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.databinding.ActivityGetHerdFiredAlertsBinding
import com.example.tpfinalmoviles.utils.HerdFiredAlertsRecycler
import com.example.tpfinalmoviles.utils.RecyclerAdapter
import com.example.tpfinalmoviles.utils.Status

class GetHerdFiredAlertsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetHerdFiredAlertsBinding
    private val viewModel = MainViewModel(AppRepository())

    private lateinit var adapter: HerdFiredAlertsRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetHerdFiredAlertsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerCardViewElement.layoutManager = LinearLayoutManager(this)

        setupListeners()

        viewModel.getCowAlerts()
    }

    private fun setupListeners() {

        binding.backBtn.setOnClickListener {
            finish()
        }

        viewModel.getCowAlerts.observe(this, {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    println(it.data)
                    adapter = HerdFiredAlertsRecycler()
                    adapter.herdFiredAlertList = it.data?.toMutableList() ?: mutableListOf()
                    binding.recyclerCardViewElement.adapter = adapter
                    Toast.makeText(this, "Funciono el GET", Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "No funciono el GET, manco", Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {

                }
            }
        })

    }
}