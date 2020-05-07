package com.example.test

class Order (val id:String, val fullname: String, val address:String, val phonenumber:String, val payment:String) {
    constructor() : this("", "","","","")
}