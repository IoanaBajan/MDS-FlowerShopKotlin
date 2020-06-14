package com.example.fifi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fifi.R
import java.lang.Exception

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        // background set for 5 second when the app starts
        val background = object : Thread(){
            override fun run(){
                try{
                    Thread.sleep(5000)

                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

}
