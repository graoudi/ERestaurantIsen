package fr.isen.kenza.erestaurant.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.kenza.erestaurant.R
import fr.isen.kenza.erestaurant.databinding.ActivityCategoriesBinding
import fr.isen.kenza.erestaurant.databinding.DetailItemsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: DetailItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_items)

        binding = DetailItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}