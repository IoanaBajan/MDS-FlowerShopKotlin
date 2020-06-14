package com.example.fifi.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.*
import com.example.fifi.model.Bouquet
import com.example.fifi.model.Item
import com.example.fifi.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {

    lateinit var v_flipper: ViewFlipper
    lateinit var searchBtn: ImageButton
    lateinit var searchBar: EditText
    var products: ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val  myImageList = intArrayOf(
            R.drawable.f1,
            R.drawable.f10,
            R.drawable.f11,
            R.drawable.f12,
            R.drawable.f13,
            R.drawable.f14,
            R.drawable.f15,
            R.drawable.f16,
            R.drawable.f17,
            R.drawable.f18
        )
        v_flipper = findViewById(R.id.v_flipper)
        for (img in myImageList) {
            flipperImages(img)
        }

        val idcustom: ImageButton = findViewById(R.id.customBouquet)
        idcustom.setOnClickListener {
            val intent = Intent(this@HomeActivity, BouquetActivity::class.java)
            startActivity(intent)
        }

        val btnprofile: ImageButton= findViewById(R.id.profile)
        btnprofile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        searchBtn = findViewById(R.id.idSearchBtn)

        searchBtn.setOnClickListener {
            showSearchBox()
        }
        val shopCart: ImageButton = findViewById(R.id.cart)
        shopCart.setOnClickListener {
            val intent = Intent(this@HomeActivity, CartActivity::class.java)
            intent.putExtra("products", products)
            startActivity(intent)
        }


        val idbutton1: Button = findViewById(R.id.button1)
        val idbutton2: Button = findViewById(R.id.button2)
        val idbutton3: Button = findViewById(R.id.button3)
        val idbutton4: Button = findViewById(R.id.button4)
        val idbutton5: Button = findViewById(R.id.button5)
        val idbutton6: Button = findViewById(R.id.button6)
        val idbutton7: Button = findViewById(R.id.button7)
        val idbutton8: Button = findViewById(R.id.button8)
        val idbutton9: Button = findViewById(R.id.button9)
        val idbutton10: Button = findViewById(R.id.button10)
        val idbutton11: Button = findViewById(R.id.button11)
        val idbutton12: Button = findViewById(R.id.button12)
        val idbutton13: Button = findViewById(R.id.button13)
        val idbutton14: Button = findViewById(R.id.button14)
        val idbutton15: Button = findViewById(R.id.button15)
        val idbutton16: Button = findViewById(R.id.button16)
        val idbutton17: Button = findViewById(R.id.button17)
        val idbutton18: Button = findViewById(R.id.button18)
        idbutton1.setOnClickListener {
            val i = Item("Scented Lavender", "230", "f1")
            if(products.find{it.name == "Scented Lavender"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton2.setOnClickListener {
            val i = Item("Simply Pink", "150", "f1")
            if(products.find{it.name == "Simply Pink"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton3.setOnClickListener {
            val i = Item("Rose bud", "140", "f1")
            if(products.find{it.name == "Rose bud"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton4.setOnClickListener {
            val i = Item("Peonies", "250", "f1")
            if(products.find{it.name == "Peonies"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton5.setOnClickListener {
            val i = Item("Romantic", "300", "f1")
            if(products.find{it.name == "Romantic"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton6.setOnClickListener {
            val i = Item("Blue Bell", "160", "f1")
            if(products.find{it.name == "Blue Bell"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton7.setOnClickListener {
            val i = Item("Blues", "250", "f1")
            if(products.find{it.name == "Blues"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton8.setOnClickListener {
            val i = Item("Casa Blanca", "125", "f1")
            if(products.find{it.name == "Casa Blanca"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton9.setOnClickListener {
            val i = Item("Citrus", "200", "f1")
            if(products.find{it.name == "Citrus"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton10.setOnClickListener {
            val i = Item("Glaze", "150", "f1")
            if(products.find{it.name == "Glaze"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton11.setOnClickListener {
            val i = Item("Godess", "250", "f1")
            if(products.find{it.name == "Godess"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton12.setOnClickListener {
            val i = Item("Purity", "130", "f1")
            if(products.find{it.name == "Purity"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton13.setOnClickListener {
            val i = Item("Snapdragons", "150", "f1")
            if(products.find{it.name == "Snapdragons"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton14.setOnClickListener {
            val i = Item("Scented Spring", "170", "f1")
            if(products.find{it.name == "Scented Spring"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton15.setOnClickListener {
            val i = Item("Secret Garden", "230", "f1")
            if(products.find{it.name == "Secret Garden"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton16.setOnClickListener {
            val i = Item("Star Gazer", "200", "f1")
            if(products.find{it.name == "Star Gazer"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton17.setOnClickListener {
            val i = Item("Topaz", "200", "f1")
            if(products.find{it.name == "Topaz"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        idbutton18.setOnClickListener {
            val i = Item("Tulips", "110", "f1")
            if(products.find{it.name == "Tulips"} == null)
                products.add(i)
            else{
                Toast.makeText(
                    applicationContext,
                    "This item is already in the shopping cart!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun showSearchBox() {
        // create edit text
        searchBar = EditText(this)

        searchBar.layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val param = searchBar.layoutParams as RelativeLayout.LayoutParams
        param.topMargin = 100
        param.leftMargin = 200

        searchBar.maxLines = 2

        searchBar.setBackgroundColor(Color.WHITE)
        searchBar.setTextColor(Color.BLACK)
        searchBar.setHintTextColor(Color.GRAY)
        searchBar.hint = "Cauta florile tale preferate"
        searchBar.paint.color = Color.BLACK

        // add edit text
        idSearchLayout?.addView(searchBar)

        // the search beggins after the user presses enter
        searchBar.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                // select all text views containing the bouquet names
                // and set text color to black to cancel the modifications made to text from previous searches
                //1
                var t: TextView = findViewById(R.id.ScentedLavander)
                t.setTextColor(Color.BLACK)

                //2
                t = findViewById(R.id.SimplyPink)
                t.setTextColor(Color.BLACK)

                //3
                t = findViewById(R.id.name3)
                t.setTextColor(Color.BLACK)

                //4
                t = findViewById(R.id.Peonies)
                t.setTextColor(Color.BLACK)

                //5
                t = findViewById(R.id.name5)
                t.setTextColor(Color.BLACK)

                //6
                t = findViewById(R.id.BlueBell)
                t.setTextColor(Color.BLACK)

                //7
                t = findViewById(R.id.name7)
                t.setTextColor(Color.BLACK)

                //8
                t = findViewById(R.id.CasaBlanca)
                t.setTextColor(Color.BLACK)

                //9
                t = findViewById(R.id.name9)
                t.setTextColor(Color.BLACK)

                //10
                t = findViewById(R.id.Glaze)
                t.setTextColor(Color.BLACK)

                //11
                t = findViewById(R.id.name11)
                t.setTextColor(Color.BLACK)

                //12
                t = findViewById(R.id.Purity)
                t.setTextColor(Color.BLACK)

                //13
                t = findViewById(R.id.name13)
                t.setTextColor(Color.BLACK)

                //14
                t = findViewById(R.id.ScentedSpring)
                t.setTextColor(Color.BLACK)

                //15
                t = findViewById(R.id.name15)
                t.setTextColor(Color.BLACK)

                //16
                t = findViewById(R.id.StarGazer)
                t.setTextColor(Color.BLACK)

                //17
                t = findViewById(R.id.name17)
                t.setTextColor(Color.BLACK)

                //18
                t = findViewById(R.id.Tulips)
                t.setTextColor(Color.BLACK)

                // before pressing enter, the user must write something in the edit text
                if (searchBar.text.toString().trim().toLowerCase(Locale.ROOT) != "") {

                    searchBouquetsByFlower(searchBar.text.toString().trim().toLowerCase(Locale.ROOT))
                }
                // delete search bar
                (searchBar.parent as ViewManager).removeView(searchBar)
                return@OnKeyListener true
            }
            false
        })

    }

    private fun flipperImages(image:Int) {
        val imageView = ImageView(this)

        imageView.setBackgroundResource(image)
        v_flipper.addView(imageView)
        v_flipper.flipInterval = 4000
        v_flipper.isAutoStart = true

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left)
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right)

    }

    private fun searchBouquetsByFlower(inputName: String) {
        // get database reference
        val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("bouquets")
        // order by flower names
        val query: Query = database.orderByChild("flowers")

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    var inpName = inputName

                    if (inputName == "tulips")
                        inpName = "tullips"

                    for (bouquet in dataSnapshot.children) {
                        val bouquetBean =
                            Bouquet(
                                bouquet.child("id").value.toString(),
                                bouquet.child("name").value.toString(),
                                bouquet.child("price").value.toString(),
                                bouquet.child("flowers").value.toString()
                            )
                        if (bouquetBean.flowers.contains(inpName)) {
                            println(bouquetBean.flowers + " " + bouquetBean.name)

                            // select all textViews with bouquet name
                            //1
                            var t: TextView = findViewById(R.id.ScentedLavander)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //2
                            t = findViewById(R.id.SimplyPink)
                            if (bouquetBean.name == "Simply pink") // change name so it  can be compared with the one in DB
                                if (t.text == "Simply Pink")
                                    t.setTextColor(Color.MAGENTA)
                            //3
                            t = findViewById(R.id.name3)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //4
                            t = findViewById(R.id.Peonies)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //5
                            t = findViewById(R.id.name5)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //6
                            t = findViewById(R.id.BlueBell)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //7
                            t = findViewById(R.id.name7)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //8
                            t = findViewById(R.id.CasaBlanca)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //9
                            t = findViewById(R.id.name9)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //10
                            t = findViewById(R.id.Glaze)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //11
                            t = findViewById(R.id.name11)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //12
                            t = findViewById(R.id.Purity)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //13
                            t = findViewById(R.id.name13)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //14
                            t = findViewById(R.id.ScentedSpring)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //15
                            t = findViewById(R.id.name15)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //16
                            t = findViewById(R.id.StarGazer)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //17
                            t = findViewById(R.id.name17)
                            if (t.text == bouquetBean.name)
                                t.setTextColor(Color.MAGENTA)
                            //18
                            t = findViewById(R.id.Tulips)
                            if (bouquetBean.name == "Tullips") // change name so it can be compared with the one in DB
                                if (t.text == "Tulips")
                                    t.setTextColor(Color.MAGENTA)
                        }
                    }
                } else {
                    println("No results found")
                }
            }
        })
    }
}