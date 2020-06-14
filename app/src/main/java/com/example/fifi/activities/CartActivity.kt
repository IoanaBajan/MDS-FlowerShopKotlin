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

        if (intent != null) {
            itemList = intent.getSerializableExtra("products") as ArrayList<Item>

        }

        initRecyclerView()
        addDataSet(itemList)

        val btnOrder: Button = findViewById(R.id.btn_place_order)
        btnOrder.setOnClickListener {
            val intent = Intent(this, PlaceOrderActivity::class.java)
            intent.putExtra("itemList", itemList)
            startActivity(intent)
        }

    }

    private fun addDataSet(itemList: ArrayList<Item>) {
        val data = itemList
        itemAdapter.submitList(data)
        println(itemList)

    }
    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this@CartActivity )
        itemAdapter = ItemRecyclerAdapter()
        recycler_view.adapter = itemAdapter

    }
}
