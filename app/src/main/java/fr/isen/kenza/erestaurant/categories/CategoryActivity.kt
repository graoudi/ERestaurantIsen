package fr.isen.kenza.erestaurant.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.kenza.erestaurant.Data.Dish
import fr.isen.kenza.erestaurant.Data.ResultData
import fr.isen.kenza.erestaurant.HomeActivity
import fr.isen.kenza.erestaurant.RecyclerAdapter
import fr.isen.kenza.erestaurant.databinding.ActivityCategoriesBinding
import org.json.JSONObject

enum class ItemType {
    STARTER, DISHES, DESSERT;
}

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType

        binding.categorieTitle.text = getCategorieTitle(selectedItem)
        /*binding.recyclerList.adapter = RecyclerAdapter(listOf(Dish("titre", 0, "price"),
                Dish("titre", 0, "price"),
                Dish("titre", 0, "price")),
                this)
                */



        getData(getCategorieTitle(selectedItem))
    }

    private fun getCategorieTitle(item: ItemType?): String {
        return when (item) {
            ItemType.STARTER -> "Entrées"
            ItemType.DISHES -> "Plats"
            ItemType.DESSERT -> "Desserts"
            else -> ""
        }
    }

    private fun getData(category: String?) {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val requestQueue= Volley.newRequestQueue(this)
        val param = JSONObject().put("id_shop", "1")

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, param,
            {
                val dataResult = GsonBuilder().create().fromJson(it.toString(), ResultData::class.java)
                dataResult.data.firstOrNull(){
                    it.name == category
                }?.items?.let{
                items -> itemDisplay(items)
                }

                //Log.d("test json", it.toString())
            },
            { error ->
               error.printStackTrace()
            }
        )
        requestQueue.add(jsonObjectRequest)
    }


    private fun  itemDisplay(items: List<Dish>){
        binding.recyclerList.layoutManager = LinearLayoutManager(this)
        binding.recyclerList.adapter = RecyclerAdapter(items) {
            val intent = Intent(this, DetailsActivities::class.java)
            intent.putExtra("dish",it)
            startActivity(intent)
        }

    }

}
