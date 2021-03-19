package fr.isen.kenza.erestaurant.categories

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanRecord
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.isen.kenza.erestaurant.R
import fr.isen.kenza.erestaurant.databinding.CellBleDevicesBinding


class BleScanAdapter(private val listBle: MutableList<BluetoothDevice>): RecyclerView.Adapter<BleScanAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CellBleDevicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return listBle.size
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: BleScanAdapter.ViewHolder, position: Int) {
        holder.textTitle.text = listBle[position].toString()
        //holder.nameTitle.text = listBle[position].deviceName.toString()


        //holder.layout.setOnClickListener { listener.invoke(listBle[position]) }
    }

    inner class ViewHolder(binding: ConstraintLayout) : RecyclerView.ViewHolder(binding){
        val textTitle: TextView = itemView.findViewById(R.id.adressDivice)
        val nameTitle: TextView = itemView.findViewById(R.id.nameDevice)
        val layout = itemView.findViewById<View>(R.id.cellBleList)


    }
    fun addDevice(data: BluetoothDevice) {
        if (!listBle.contains(data)) {
            listBle.add(data)
          //  listBle.add(name)
        }
    }



}