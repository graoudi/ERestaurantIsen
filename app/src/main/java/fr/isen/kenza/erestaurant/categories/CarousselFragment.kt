package fr.isen.kenza.erestaurant.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.kenza.erestaurant.Data.Dish
import fr.isen.kenza.erestaurant.databinding.CarousselItemBinding

class CarousselFragment(private val entries: List<Dish>): Fragment(){

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding= CarousselItemBinding.inflate(inflater, container, false)
        val test= binding.root
        return test
    }

    /*override fun onCreate(binding: CarousselItemBinding, position : Int, savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        Picasso.get()
                .load(entries[position].urlImage())
                .into(binding.imageCarousel)

    } */
//View pager

}
