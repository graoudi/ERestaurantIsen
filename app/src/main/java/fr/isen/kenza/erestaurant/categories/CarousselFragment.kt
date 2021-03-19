package fr.isen.kenza.erestaurant.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import fr.isen.kenza.erestaurant.Data.Dish
import fr.isen.kenza.erestaurant.R
import fr.isen.kenza.erestaurant.databinding.CarousselItemBinding


private lateinit var binding: CarousselItemBinding

class CarouselFragment(): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CarousselItemBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString(ARG_OBJECT)?.let {
            Picasso.get()
                .load(it)
                .into(binding.imageCarousel)
        }
    }

    companion object {
        const val ARG_OBJECT = "object"
    }
}