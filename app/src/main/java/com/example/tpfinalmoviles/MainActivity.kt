package com.example.tpfinalmoviles

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tpfinalmoviles.data.model.*
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.databinding.ActivityMainBinding
import com.example.tpfinalmoviles.ui.*
import com.example.tpfinalmoviles.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel(AppRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkConnection()

        viewModel.toggleSession(Session(true))

        setListeners()

    }

    private fun setListeners() {
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

        binding.addCowAlertBtn.setOnClickListener {
            startActivity(Intent(this, AddCowAlertActivity::class.java))
        }

        binding.addHerdAlertBtn.setOnClickListener {
            startActivity(Intent(this, AddHerdAlertActivity::class.java))
        }

        binding.getCowAlertBtn.setOnClickListener {
            startActivity(Intent(this, GetCowFiredAlertsActivity::class.java))
        }

        binding.getHerdAlertBtn.setOnClickListener {
            startActivity(Intent(this, GetHerdFiredAlertsActivity::class.java))
        }

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

}