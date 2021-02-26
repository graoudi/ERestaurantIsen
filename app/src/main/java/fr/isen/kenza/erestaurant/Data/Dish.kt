package fr.isen.kenza.erestaurant.Data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//classe qui sert qu'a contenir des donnees
data class Dish(@SerializedName("name_fr") val name: String,
                @SerializedName("image") val image: List<String>,
                @SerializedName("ingredient") val ingredient: List<Ingredient>,
                @SerializedName("price") val price: List<Price>)

    : Serializable {

    fun urlImage(): String?{
       return if (image.isNotEmpty() && image[0].isNotEmpty()){
           image[0]
       }
        else{
           null
       }
    }



}


