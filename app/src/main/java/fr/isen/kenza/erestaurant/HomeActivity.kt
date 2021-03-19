package fr.isen.kenza.erestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import fr.isen.kenza.erestaurant.categories.BleScanActivity
import fr.isen.kenza.erestaurant.categories.CategoryActivity
import fr.isen.kenza.erestaurant.categories.ItemType
import fr.isen.kenza.erestaurant.databinding.HomeActivityBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding
    private val myTag ="Destroy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = fr.isen.kenza.erestaurant.databinding.HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainAction = findViewById<Button>(R.id.buttonStarters)

        val intent = Intent(this, CategoryActivity::class.java) //redirection vers category
        binding.buttonStarters.setOnClickListener{
            //l'utisateur a clique

            val text = "Commandez vos entrées!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration) //affiche msg a l'ecran
            toast.show()
            //startActivity(intent)
            startCategoryActivity(ItemType.STARTER)
        }

        binding.buttonDishes.setOnClickListener{
            //l'utisateur a clique

            val text = "Commandez vos plats!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
           // startActivity(intent)
            startCategoryActivity(ItemType.DISHES)
        }
        binding.buttonDessert.setOnClickListener{
            //l'utisateur a clique

            val text = "Commandez vos desserts!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
           // startActivity(intent)
            startCategoryActivity(ItemType.DESSERT)
        }
        binding.buttonBle.setOnClickListener{
            val intent = Intent(this, BleScanActivity::class.java)
            startActivity(intent)
        }


    }




    private fun startCategoryActivity(item: ItemType) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(CATEGORY_NAME, item)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }



    override fun onDestroy() { //quand l'activite est detruite
        super.onDestroy()
        Log.i(myTag, "onDestroy")
        Toast.makeText(baseContext, "onDestroy", Toast.LENGTH_SHORT).show()

    }
}