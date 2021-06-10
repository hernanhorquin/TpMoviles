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

        /*
        // START SESSION
        viewModel.toggleSession(Session(true))

        // GET COW
        val getCowResult = viewModel.getCow(1)

        // POST COW
        val cow = Cow(null, 2, 3, "2018-10-09", 1, 5.3, null)
        viewModel.createCow(cow)

        // GET HERD
        val getHerdResult = viewModel.getHerd(1)

        // POST HERD
        val herd = Herd(null, "Cuarto rodeo")
        viewModel.createHerd(herd)

        // POST COW ALERT
        val cowAlert = CowAlert(null,2, null, 7.7, 3.3)
        viewModel.addCowAlert(cowAlert)

        // POST HERD ALERT
        val herdAlert = HerdAlert(null, 1, 9.0, 6.0)
        viewModel.addHerdAlert(herdAlert)

        // GET COW ALERTS
        viewModel.getCowAlerts()

        // GET HERD ALERTS
        viewModel.getHerdAlerts()
        */

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

        viewModel.getCow.observe(this, {
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