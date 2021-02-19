package fr.isen.kenza.erestaurant.categories

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import fr.isen.kenza.erestaurant.HomeActivity
import fr.isen.kenza.erestaurant.RecyclerAdapter
import fr.isen.kenza.erestaurant.databinding.ActivityCategoriesBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


enum class ItemType {
    STARTER, DISHES, DESSERT;
}


class CategoryActivity : AppCompatActivity(), RecyclerAdapter.onItemClickListener {

    private lateinit var binding: ActivityCategoriesBinding

    private var url = "http://test.api.catering.bluecodegames.com/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType

        binding.categorieTitle.text = getCategorieTitle(selectedItem)
        binding.recyclerList.adapter= RecyclerAdapter(listOf(Dish("titre", 0, "price")),this)


        binding.recyclerList.layoutManager = LinearLayoutManager(this)
    }

    private fun getCategorieTitle(item: ItemType?): String {
        return when(item) {
            ItemType.STARTER -> "STARTER"
            ItemType.DISHES -> "DISHES"
            ItemType.DESSERT -> "DESSERT"
            else -> ""
        }
    }

    override fun onItemClick(dish: Dish) {
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, "clicked", duration) //affiche msg a l'ecran
        toast.show()
        val intent = Intent (this, DetailsActivity:: class.java)
        intent.putExtra("dish", dish)
        startActivity(intent)
    }


}


