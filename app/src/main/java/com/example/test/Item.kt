package com.example.test
//..app/src/main/java/yourPackage/Product.kt
import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class Item(
    @SerializedName("description")
    var name: String? = null,
    @SerializedName("id")
    var price: String? = null,
    @SerializedName("name")
    var imageId: String? = null,
    @SerializedName("amount")
    var amount: Int? = null,
    @SerializedName("totalPrice")
    var totalPrice: Int? = null
): Serializable
