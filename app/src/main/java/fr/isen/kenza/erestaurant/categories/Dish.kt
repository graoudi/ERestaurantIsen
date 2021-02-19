package fr.isen.kenza.erestaurant.categories

import java.io.Serializable

class Dish : Serializable {
    fun titleName(string: String) {
        TODO("Not yet implemented")
    }

    fun pictureName(int: Int) {
        TODO("Not yet implemented")
    }

    fun priceItem(int: String) {
        TODO("Not yet implemented")
    }

    var titleName: String? = null
    var pictureName = 0
    var priceItem: String? = null

    constructor() {}
    constructor(titleName: String?, pictureName: Int, priceItem: String?) {
        this.titleName = titleName
        this.pictureName = pictureName
        this.priceItem = priceItem
    }
}

