package fr.isen.kenza.erestaurant.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.kenza.erestaurant.Data.Dish
import fr.isen.kenza.erestaurant.Data.Ingredient
import fr.isen.kenza.erestaurant.R
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
            }



        binding.removeButton.setOnClickListener{
            if (quantity > 0)
                quantity --
            binding.totalOrder.text = quantity.toString()
            }


        }
    }
