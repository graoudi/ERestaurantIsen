package fr.isen.kenza.erestaurant.Data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//classe qui sert qu'a contenir des donnees
data class Dish(@SerializedName("name_fr") val name: String,
                @SerializedName("images") val images: List<String>,
                @SerializedName("ingredients") val ingredients: List<Ingredient>,
                @SerializedName("prices") val prices: List<Price>)

    : Serializable {

    fun urlImage()=
       if (images != null && images.isNotEmpty() && images[0].isNotEmpty()){
           images[0]
       }
        else{
           null
       }

    fun getPrice() = prices[0].price + "€"
    fun FormatPrice()= prices[0].price  + "€"


}


