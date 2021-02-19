package fr.isen.kenza.erestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.kenza.erestaurant.categories.Dish
import fr.isen.kenza.erestaurant.databinding.DishesDetailBinding


class RecyclerAdapter(
        private val entries: List<Dish>,
        private val listener: onItemClickListener): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DishesDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return entries.size
    }


    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val dish = entries[position]
        holder.textTitle.text = dish.titleName
        holder.textPrice.text = dish.priceItem
        holder.itemView.setOnClickListener { listener.onItemClick(dish) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textTitle: TextView = itemView.findViewById(R.id.categoryName)
        var textPicture: ImageView  = itemView.findViewById(R.id.catagoryImage)
        var textPrice: TextView = itemView.findViewById(R.id.categoryprice)

    }
    interface  onItemClickListener{
        fun onItemClick(dish: Dish)
    }
}







