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

        viewModel.getHerdAlerts()
    }

    private fun setupListeners() {

        binding.backBtn.setOnClickListener {
            finish()
        }

        viewModel.getHerdAlerts.observe(this, {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    adapter = HerdFiredAlertsRecycler()
                    adapter.herdFiredAlertList = it.data?.toMutableList() ?: mutableListOf()
                    binding.recyclerCardViewElement.adapter = adapter
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {

                }
            }
        })

    }
}