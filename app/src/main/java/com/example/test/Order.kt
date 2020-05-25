package com.example.test

import com.google.gson.annotations.SerializedName
import java.io.Serializable

object Order:Serializable {

    @SerializedName("id")
    var  id:String
    @SerializedName("fullname")
    var  fullname:String
    @SerializedName("address")
    var  address:String
    @SerializedName("phonenumber")
    var  phonenumber:String
    @SerializedName("payment")
    var  payment:String
    @SerializedName("message")
    var  message:String
    @SerializedName("date")
    var date:String
    @SerializedName("price")
    var price:String
    @SerializedName("command")
    var  command:String

    init {
        id = ""
        fullname = ""
        address = ""
        phonenumber = ""
        payment = ""
        message = ""
        date = ""
        price = ""
        command = ""
    }

}