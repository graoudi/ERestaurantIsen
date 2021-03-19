package fr.isen.kenza.erestaurant.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import fr.isen.kenza.erestaurant.Data.Dish
import fr.isen.kenza.erestaurant.RecyclerAdapter
import fr.isen.kenza.erestaurant.databinding.ActivityDetailsActivitiesBinding

class DetailsActivities : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsActivitiesBinding
    private var quantity = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as? Dish

        //  binding.dishTitle.text = "hello"
        binding.dishTitle.text = dish?.name
        binding.dishIngredient.text = dish?.ingredients?.map { it.name }?.joinToString(", ")
        binding.dishPrice.text = dish?.getPrice()


        if(dish?.urlImage().isNullOrEmpty()){
            Picasso.get()
                    .load("http://i.imgur.com/DvpvklR.png")
                    .into(binding.imageCarou)
        }
        else {
            Picasso.get()
                    .load(dish?.urlImage())
                    .into(binding.imageCarou)
        }





        binding.addButton.setOnClickListener {
            quantity ++
            binding.totalOrder.text = quantity.toString()
            var test = dish?.getJustPrice()?.toFloat()?.times(quantity)
            binding.total.text = test.toString() + "euros"
            }



        binding.removeButton.setOnClickListener{
            if (quantity > 0)
                quantity --
            binding.totalOrder.text = quantity.toString()
            var test = dish?.getJustPrice()?.toFloat()?.times(quantity)
            binding.total.text = test.toString() + "euros"
            }


        }
    }
