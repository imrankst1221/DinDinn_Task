package infix.imrankst1221.dindinntask.model

import com.google.gson.annotations.SerializedName

class FoodMenu {
    @SerializedName("root")
    var root: List<Root> = arrayListOf()
}

class Root{
    @SerializedName("filters")
    var filters: List<Filter> = arrayListOf()
    @SerializedName("category")
    var categorys: List<Category> = arrayListOf()
    @SerializedName("items")
    var items: List<Item> = arrayListOf()
}

class Filter {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
}

class Category {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
}

class Item {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("category_id")
    var categoryId: Int = 0
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