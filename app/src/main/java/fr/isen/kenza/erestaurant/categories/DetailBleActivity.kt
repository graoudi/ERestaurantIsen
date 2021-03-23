package fr.isen.kenza.erestaurant.categories

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
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

    private lateinit var data: BluetoothDevice

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val testBle = intent.getSerializableExtra("ble") as? ScanResult
      // val dataBle = intent.getSerializableExtra("bluetooth") as? BluetoothDevice
   //   binding.detailBluetooth.text =  "hello"
        data = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)  ?: error("Missing BluetoothDevice from MainActivity!")
      binding.detailBluetooth.text =  data?.address

    }
}