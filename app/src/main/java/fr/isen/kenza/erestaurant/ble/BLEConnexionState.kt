package fr.isen.kenza.erestaurant.ble

import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothProfile
import androidx.annotation.StringRes
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import fr.isen.kenza.erestaurant.R
class BLEConnexionState(title: String, items: List<BluetoothGattCharacteristic>) :
    ExpandableGroup<BluetoothGattCharacteristic>(title, items)