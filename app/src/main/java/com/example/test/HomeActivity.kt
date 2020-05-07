package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    lateinit var v_flipper: ViewFlipper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var  myImageList = intArrayOf(R.drawable.f1, R.drawable.f10,R.drawable.f11, R.drawable.f12,
            R.drawable.f13,R.drawable.f14,R.drawable.f15,R.drawable.f16,R.drawable.f17,R.drawable.f18)
        v_flipper = findViewById(R.id.v_flipper)
        for (img in myImageList) {
            flipperImages(img)
        }
    }

    private fun flipperImages(image:Int) {
        var imageView : ImageView = ImageView(this)

        imageView.setBackgroundResource(image)
        v_flipper.addView(imageView)
        v_flipper.setFlipInterval(4000)
        v_flipper.setAutoStart(true)

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left)
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right)

    }
}