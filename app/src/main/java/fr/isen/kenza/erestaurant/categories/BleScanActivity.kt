package fr.isen.kenza.erestaurant.categories


import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi

import androidx.core.view.isVisible
import fr.isen.kenza.erestaurant.R
import fr.isen.kenza.erestaurant.databinding.ActivityBleScanBinding


private lateinit var binding: ActivityBleScanBinding

class BleScanActivity : AppCompatActivity() {


    private var isScanning = false
    private val bluetoothAdapter: BluetoothAdapter? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager?.adapter

        when{
            //premier cas
            !isDeviceHasBLESupport()  ||  bluetoothAdapter == null -> {
                Toast.makeText(this, "cet appareil n'est pas compatible", Toast.LENGTH_SHORT).show()
            }
            //si je ne suis pas dans le premier cas alors
            //ici il a le ble mais pas active
            !bluetoothAdapter.isEnabled ->{
                //je dois activer le ble
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT) //attend un resultat quand c'est fini

            }
            else -> {
            // youpi on peut faire de ble

            }

        }
        isDeviceHasBLESupport()

        binding.playButton.setOnClickListener{
            togglePlayPauseAction()
        }
        binding.bleScanTitle.setOnClickListener{
            togglePlayPauseAction()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_OK){

        }
    }

    private fun isDeviceHasBLESupport(): Boolean {
      return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
    }


    private fun togglePlayPauseAction(){
        isScanning =! isScanning
        if(isScanning){
            binding.bleScanTitle.text= getString(R.string.ble_scan_play_title)
            binding.playButton.setImageResource(R.drawable.ic_play)
            binding.progressBar.isVisible= true
            binding.divider.isVisible= false

        }
        else {

            binding.bleScanTitle.text= getString(R.string.ble_scan_pause_title)
            binding.playButton.setImageResource(R.drawable.ic_play)
            binding.progressBar.isVisible= false
            binding.divider.isVisible= true

        }
    }


companion object {
    const private val REQUEST_ENABLE_BT = 33
}




}

