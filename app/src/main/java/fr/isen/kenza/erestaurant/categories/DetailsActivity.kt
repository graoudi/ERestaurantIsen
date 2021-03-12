package fr.isen.kenza.erestaurant.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.kenza.erestaurant.Data.Dish
import fr.isen.kenza.erestaurant.HomeActivity
import fr.isen.kenza.erestaurant.R
import fr.isen.kenza.erestaurant.databinding.ActivityCategoriesBinding
import fr.isen.kenza.erestaurant.databinding.DetailItemsBinding
import java.lang.Integer.max

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: DetailItemsBinding
    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_items)

        binding = DetailItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as? Dish

       dish?.let { testView(it) }


}
    private fun shop(dish: Dish) {
        var totalprice = dish.getJustPrice().toFloat() * quantity
        binding.totalOrder.text = totalprice.toString() + "€"
    }

    private fun testView(dish: Dish) {
        binding.dishTitle.text = dish.name
        binding.dishIngredient.text = dish.ingredients.map{ it.name }.joinToString(", ")
        binding.dishPrice.text = dish.getPrice()
        shop(dish)



    }
}