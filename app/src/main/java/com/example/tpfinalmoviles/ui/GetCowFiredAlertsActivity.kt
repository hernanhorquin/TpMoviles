package com.example.tpfinalmoviles.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.databinding.ActivityGetCowFiredAlertsBinding
import com.example.tpfinalmoviles.utils.RecyclerAdapter
import com.example.tpfinalmoviles.utils.Status

class GetCowFiredAlertsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityGetCowFiredAlertsBinding
    private val viewModel = MainViewModel(AppRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetCowFiredAlertsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerCardViewElement.layoutManager = LinearLayoutManager(this)
        binding.recyclerCardViewElement.adapter = RecyclerAdapter()

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