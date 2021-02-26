package fr.isen.kenza.erestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.kenza.erestaurant.Data.Dish
import fr.isen.kenza.erestaurant.databinding.DishesDetailBinding


class RecyclerAdapter(
    private val entries: List<Dish>,
    private val listener:  (Dish) -> Unit): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DishesDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return entries.size
    }


    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val dish = entries[position]
        holder.textTitle.text = entries[position].name
        holder.textPrice.text = entries[position].FormatPrice()
        if(entries[position].urlImage().isNullOrEmpty()){
            Picasso.get()
                .load("http://i.imgur.com/DvpvklR.png")
                .into(holder.textPicture)
        }
        else{
        Picasso.get()
            .load(dish.urlImage())
            .into(holder.textPicture) }

        holder.layout.setOnClickListener { listener.invoke(entries[position]) }
    }

  inner class ViewHolder(binding : DishesDetailBinding) : RecyclerView.ViewHolder(binding.root){
      val textTitle = binding.categoryName
      val textPicture= binding.catagoryImage
      val textPrice = binding.categoryprice
      val layout = itemView.findViewById<View>(R.id.cellDetail)


  }
    interface  onItemClickListener{

        fun onItemClick(dish: Dish)
    }
}







