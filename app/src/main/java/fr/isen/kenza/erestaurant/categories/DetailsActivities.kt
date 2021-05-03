package fr.isen.kenza.erestaurant.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import fr.isen.kenza.erestaurant.Data.Dish
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




        var samplesImage = arrayOf(dish?.images?.get(0))
        val carouselView = findViewById<CarouselView>(R.id.imageCarou)


       var imageListener : ImageListener = object : ImageListener {
           override fun setImageForPosition(position: Int, imageView: ImageView?) {

               if (dish?.urlImage().isNullOrEmpty()) {
                   Picasso.get()
                       .load("http://i.imgur.com/DvpvklR.png")
                       .into(imageView)
               } else {
                   Picasso.get()
                       .load(dish?.urlImage())
                       .into(imageView)
               }

           }
       }
        carouselView.setPageCount(samplesImage.size)
        carouselView.setImageListener(imageListener)

        if (dish != null){
            binding.imageCarou.pageCount = dish.images.size
        }

        binding.imageCarou.setImageListener(imageListener)
       // binding.imageCarou.setImageListener(imageListener)





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
