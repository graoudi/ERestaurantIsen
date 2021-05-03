package fr.isen.kenza.erestaurant.ble



import android.bluetooth.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.kenza.erestaurant.R
import fr.isen.kenza.erestaurant.databinding.ActivityDetailBleBinding
import java.util.*



@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class DetailBleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBleBinding

    var statut: String = "statut :  "
    var bluetoothGatt: BluetoothGatt? = null
    private var TAG: String = "My Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val device: BluetoothDevice? = intent.getParcelableExtra("ble_device")

        bluetoothGatt = device?.connectGatt(this, true, gattCallback)
        binding.deviceName.text = device?.name ?: "Appareil Inconnu"
        binding.deviceBleName.text = device?.address
        connectToDevice(device)
    }

    private fun connectToDevice(device: BluetoothDevice?) {
        bluetoothGatt= device?.connectGatt(this, false, gattCallback)
    }
    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(
            gatt: BluetoothGatt,
            status: Int,
            newState: Int
        ) {
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    runOnUiThread {
                        statut += STATE_CONNECTED
                        binding.deviceStatut.text = statut
                    }
                    bluetoothGatt?.discoverServices()
                    Log.i(TAG, "Connected to GATT server.")
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    runOnUiThread {
                        statut += STATE_DISCONNECTED
                        binding.deviceStatut.text = statut
                    }
                    Log.i(TAG, "Disconnected from GATT server.")
                }
            }
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
            runOnUiThread {
                binding.recyclerBLEDetail.adapter?.notifyDataSetChanged()
            }
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            runOnUiThread {
                binding.recyclerBLEDetail.adapter?.notifyDataSetChanged()
            }
        }
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
            runOnUiThread {
                binding.recyclerBLEDetail.adapter?.notifyDataSetChanged()
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            runOnUiThread {
                binding.recyclerBLEDetail.adapter = BLEDeviceAdapter(
                    gatt,
                    gatt?.services?.map {
                        BLEConnexionState(
                            it.uuid.toString(),
                            it.characteristics
                        )
                    }?.toMutableList() ?: arrayListOf()
                    , this@DetailBleActivity
                )
                binding.recyclerBLEDetail.layoutManager = LinearLayoutManager(this@DetailBleActivity)

            }
        }


    }

    companion object {
        private const val STATE_DISCONNECTED = "Déconnecte"
        private const val STATE_CONNECTING = "En cour de connection "
        private const val STATE_CONNECTED = "Connecte"
        const val ACTION_GATT_CONNECTED = "com.example.bluetooth.le.ACTION_GATT_CONNECTED"
    }

}
