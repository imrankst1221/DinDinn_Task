package infix.imrankst1221.dindinntask.model

import com.google.gson.annotations.SerializedName

class FoodMenu {
    @SerializedName("category")
    var category: List<Category>? = null
}

class Category {
    @SerializedName("filters")
    var filters: List<Filter>? = null
    @SerializedName("items")
    var items: List<Item>? = null
}

class Filter {
    @SerializedName("name")
    var name: String? = null
}


class Item {
    @SerializedName("name")
    var name: String? = null
    @SerializedName("details")
    var details: String? = null
    @SerializedName("size")
    var size: String? = null
    @SerializedName("price")
    var price: Double? = null
    @SerializedName("image")
    var image: String? = null
}