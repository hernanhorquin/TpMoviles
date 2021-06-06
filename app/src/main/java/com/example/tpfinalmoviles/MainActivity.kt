package com.example.tpfinalmoviles

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tpfinalmoviles.data.repository.CowRepository
import com.example.tpfinalmoviles.databinding.ActivityMainBinding
import com.example.tpfinalmoviles.ui.*
import com.example.tpfinalmoviles.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel(CowRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkConnection()

        setListeners()

    }

    private fun setListeners() {
        binding.createCow.setOnClickListener {
            viewModel.getCow()
        }
        binding.addCowBtn.setOnClickListener {
            startActivity(Intent(this, AddCowActivity::class.java))
        }
        binding.addHerdBtn.setOnClickListener {
            startActivity(Intent(this, AddHerdActivity::class.java))
        }
        binding.getCowBtn.setOnClickListener {
            startActivity(Intent(this, GetCowActivity::class.java))
        }
        binding.getHerdBtn.setOnClickListener {
            startActivity(Intent(this, GetHerdActivity::class.java))
        }
        viewModel.getCow.observe(this, {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
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

    private fun checkConnection() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        networkInfo?.let {
            if (it.isConnected)
                println("We are connected")
            else
                println("No network connection available")
        }
    }

    companion object {

    }
}