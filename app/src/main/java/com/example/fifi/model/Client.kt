package com.example.fifi.model


class Client (val id:String, val firstName:String, val lastName:String, val username:String,val password:String, val email:String){
    constructor() : this("", "","","","","")
}