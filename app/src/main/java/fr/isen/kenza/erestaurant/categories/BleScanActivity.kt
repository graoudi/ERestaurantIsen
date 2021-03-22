package fr.isen.kenza.erestaurant.categories


import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.kenza.erestaurant.R
import fr.isen.kenza.erestaurant.RecyclerAdapter
import fr.isen.kenza.erestaurant.databinding.ActivityBleScanBinding



class BleScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBleScanBinding

    private val permissionDeniedList = ArrayList<String>()
    private var isScanning = false
    private var bluetoothAdapter: BluetoothAdapter? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private var bluetoothLeScanner: BluetoothLeScanner? = null
    private var scanning = false
    private var handler: Handler? = null
    private  var leDeviceListAdapter: BleScanAdapter? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


         bluetoothAdapter= getSystemService(BluetoothManager::class.java)?.adapter

        startBLEifPossible()

        isDeviceHasBLESupport()

        binding.playButton.setOnClickListener{
            togglePlayPauseAction()
        }
        binding.bleScanTitle.setOnClickListener{
            togglePlayPauseAction()
        }


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startBLEifPossible() {

        val deniedPermissions = permissionDeniedList.toTypedArray()
        when {
            //premier cas
            !isDeviceHasBLESupport() || bluetoothAdapter == null -> {
                Toast.makeText(this, "cet appareil n'est pas compatible", Toast.LENGTH_SHORT).show()
            }

            !(bluetoothAdapter?.isEnabled  ?: false) -> {
                //je dois activer le ble
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED -> {
                ActivityCompat.requestPermissions( this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSION_LOCATION)
            }
            else -> {
                // youpi on peut faire de ble
                bluetoothLeScanner = bluetoothAdapter?.bluetoothLeScanner
                initRecyclerDevice()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun scanLeDevice() {
        bluetoothLeScanner?.let { scanner ->
            if (!scanning) { // Stops scanning after a pre-defined scan period.
                handler?.postDelayed({
                    scanning = false
                    scanner.stopScan(leScanCallback)
                }, SCAN_PERIOD)
                scanning = true
                scanner.startScan(leScanCallback)
            } else {
                scanning = false
                scanner.stopScan(leScanCallback)
            }
        }
    }

    // Device scan callback.
    private val leScanCallback: ScanCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val test = result.scanRecord?.deviceName?.toString()

            super.onScanResult(callbackType, result)
            leDeviceListAdapter?.addDevice(result)
           // leDeviceListAdapter.addDevice(test)

            leDeviceListAdapter?.notifyDataSetChanged()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_OK){
            startBLEifPossible()
        }
    }

    private fun isDeviceHasBLESupport(): Boolean {
      return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun togglePlayPauseAction(){
        isScanning =! isScanning
        if(isScanning){
            binding.bleScanTitle.text= getString(R.string.ble_scan_play_title)
            binding.playButton.setImageResource(R.drawable.ic_play)
            binding.progressBar.isVisible= true
            binding.divider.isVisible= false
            scanLeDevice()

        }
        else {

            binding.bleScanTitle.text= getString(R.string.ble_scan_pause_title)
            binding.playButton.setImageResource(R.drawable.ic_play)
            binding.progressBar.isVisible= false
            binding.divider.isVisible= true

        }



    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun  initRecyclerDevice() {

        leDeviceListAdapter = BleScanAdapter((mutableListOf())){
            val intent = Intent(this, DetailBleActivity::class.java)
            intent.putExtra("ble",it)
            startActivity(intent)
        }
         binding.recyclerBleScan.layoutManager = LinearLayoutManager(this)
         binding.recyclerBleScan.adapter = leDeviceListAdapter

/*
        binding.recyclerBleScan.adapter = BleScanAdapter((mutableListOf())) {
            val intent = Intent(this, DetailBleActivity::class.java)
            intent.putExtra("essaiBle", it)
            startActivity(intent)
        }
        binding.recyclerBleScan.layoutManager = LinearLayoutManager(this)
        binding.recyclerBleScan.adapter = leDeviceListAdapter
*/
    }

    companion object {
    const private val REQUEST_ENABLE_BT = 33
    const private val REQUEST_PERMISSION_LOCATION = 22
    const private val SCAN_PERIOD: Long = 10000

    }
}

