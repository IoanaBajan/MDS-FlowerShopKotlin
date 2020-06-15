package com.example.fifi.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fifi.model.Item
import com.example.fifi.R
import com.example.tes.ItemRecyclerAdapter
import kotlinx.android.synthetic.main.activity_shoppingcart.*

class CartActivity:AppCompatActivity() {
    private lateinit var itemAdapter: ItemRecyclerAdapter
    private var itemList: ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppingcart)

        //receive the products that must be added to the shopping cart
        if (intent != null) {
            itemList = intent.getSerializableExtra("products") as ArrayList<Item>

        }

        //initialize the recyclerview- container for the photos and
        // submit the list of products to be displayed
        initRecyclerView()
        addDataSet(itemList)

        //when clicking the button place order send the list of products from the shopping cart
        //the elimination of a product is not implemented
        val btnOrder: Button = findViewById(R.id.btn_place_order)
        btnOrder.setOnClickListener {
            val intent = Intent(this, PlaceOrderActivity::class.java)
            intent.putExtra("itemList", itemList)
            startActivity(intent)
        }

    }

    private fun addDataSet(itemList: ArrayList<Item>) {
        //calls a function from adapter which receives the product list
        val data = itemList
        itemAdapter.submitList(data)

    }
    private fun initRecyclerView() {
        //instantiates the container
        recycler_view.layoutManager = LinearLayoutManager(this@CartActivity )
        itemAdapter = ItemRecyclerAdapter()
        recycler_view.adapter = itemAdapter

    }
}
