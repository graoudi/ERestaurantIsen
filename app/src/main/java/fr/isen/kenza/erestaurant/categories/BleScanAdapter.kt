package fr.isen.kenza.erestaurant.categories

import android.bluetooth.le.ScanResult
import android.content.Intent
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


class BleScanAdapter
(private val listBle: MutableList<ScanResult>
 ,private val listener:  (ScanResult) -> Unit
    ): RecyclerView.Adapter<BleScanAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CellBleDevicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return listBle.size
    }


   @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder( holder: ViewHolder, position: Int) {
        holder.textTitle.text = listBle[position].device.toString()
       holder.nameTitle.text = listBle[position].scanRecord?.deviceName.toString()
        holder.numID.text = listBle[position].scanRecord?.advertiseFlags.toString()

      holder.layout.setOnClickListener { listener.invoke(listBle[position]) }

    }




     class ViewHolder(binding: ConstraintLayout) : RecyclerView.ViewHolder(binding){
        val textTitle: TextView = itemView.findViewById(R.id.adressDevice)
        val nameTitle: TextView = itemView.findViewById(R.id.nameDevice)
        val numID: TextView = itemView.findViewById(R.id.buttonNumber)
        val layout = itemView.findViewById<View>(R.id.cellBleList)

    }


    fun addDevice(data: ScanResult ) {
        if (!listBle.contains(data)) {
            listBle.add(data)

          //  listBle.add(name)
        }
    }



}