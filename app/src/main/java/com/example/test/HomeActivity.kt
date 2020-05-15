package com.example.test

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.*
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    lateinit var v_flipper: ViewFlipper
    lateinit var searchBtn: ImageButton
    lateinit var searchBar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var  myImageList = intArrayOf(R.drawable.f1, R.drawable.f10,R.drawable.f11, R.drawable.f12,
            R.drawable.f13,R.drawable.f14,R.drawable.f15,R.drawable.f16,R.drawable.f17,R.drawable.f18)
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

    }

    fun showSearchBox() {
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
        searchBar.setHint("Cauta florile tale preferate")
        searchBar.paint.color = Color.BLACK;

        // add edit text
        idSearchLayout?.addView(searchBar)

        // the search beggins after the user presses enter
        searchBar.setOnKeyListener(View.OnKeyListener { view, keyCode, event ->
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

                // beefore pressing enter, the user must write something in the edit text
                if (!(searchBar.text.toString().trim().toLowerCase().equals(""))) {

                    searchBouquetsByFlower(searchBar.text.toString().trim().toLowerCase())
                }
                // delete search bar
                (searchBar.getParent() as ViewManager).removeView(searchBar)
                return@OnKeyListener true
            }
            false
        })

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

    fun searchBouquetsByFlower(inputName: String) {
        // get database reference
        var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("bouquets")
        // order by flower names
        val query: Query = database.orderByChild("flowers")

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    var inpName = inputName

                    if (inputName.equals("tulips"))
                        inpName = "tullips"

                    for (bouquet in dataSnapshot.children) {
                        val bouquetBean: Bouquet = Bouquet(
                            bouquet.child("id").getValue().toString(),
                            bouquet.child("name").getValue().toString(),
                            bouquet.child("price").getValue().toString(),
                            bouquet.child("flowers").getValue().toString()
                        )

                        if (bouquetBean.flowers.contains(inpName)) {
                            println(bouquetBean.flowers + " " + bouquetBean.name)

                            // select all textviews with bouquet name
                            //1
                            var t: TextView = findViewById(R.id.ScentedLavander)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //2
                            t = findViewById(R.id.SimplyPink)
                            if (bouquetBean.name.equals("Simply pink")) // change name so it  can be compared with the one in DB
                                if (t.text.equals("Simply Pink"))
                                    t.setTextColor(Color.MAGENTA)
                            //3
                            t = findViewById(R.id.name3)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //4
                            t = findViewById(R.id.Peonies)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //5
                            t = findViewById(R.id.name5)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //6
                            t = findViewById(R.id.BlueBell)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //7
                            t = findViewById(R.id.name7)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //8
                            t = findViewById(R.id.CasaBlanca)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //9
                            t = findViewById(R.id.name9)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //10
                            t = findViewById(R.id.Glaze)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //11
                            t = findViewById(R.id.name11)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //12
                            t = findViewById(R.id.Purity)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //13
                            t = findViewById(R.id.name13)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //14
                            t = findViewById(R.id.ScentedSpring)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //15
                            t = findViewById(R.id.name15)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //16
                            t = findViewById(R.id.StarGazer)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //17
                            t = findViewById(R.id.name17)
                            if (t.text.equals(bouquetBean.name))
                                t.setTextColor(Color.MAGENTA)
                            //18
                            t = findViewById(R.id.Tulips)
                            if (bouquetBean.name.equals("Tullips")) // change name so it can be compared with the one in DB
                                if (t.text.equals("Tulips"))
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