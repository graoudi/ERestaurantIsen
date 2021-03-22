package fr.isen.kenza.erestaurant.categories

import android.bluetooth.le.ScanResult
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import fr.isen.kenza.erestaurant.Data.Dish
import fr.isen.kenza.erestaurant.R
import fr.isen.kenza.erestaurant.databinding.ActivityDetailBleBinding
import fr.isen.kenza.erestaurant.databinding.ActivityDetailsActivitiesBinding

class DetailBleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBleBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val test = intent.getSerializableExtra("test") as? ScanResult
        binding.detailBle.text =  test?.device.toString()


    }
}