package infix.imrankst1221.dindinntask.model

import com.google.gson.annotations.SerializedName

class FoodMenu {
    @SerializedName("category")
    var category: List<Category> = arrayListOf()
}

class Category {
    @SerializedName("filters")
    var filters: List<Filter> = arrayListOf()
    @SerializedName("items")
    var items: List<Item> = arrayListOf()
}

class Filter {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
}

class Item {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("count")
    var count: Int = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("details")
    var details: String = ""
    @SerializedName("size")
    var size: String = ""
    @SerializedName("price")
    var price: Double = 0.0
    @SerializedName("image")
    var image: String = ""
}