package com.example.tes

import com.example.fifi.model.Item
import com.example.fifi.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.cart_item_layout.view.*

class ItemRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Item> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout,parent, false)
        )

    }

    override  fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemViewHolder -> {
                holder.bind(items.get(position))
            }
        }


    }

    fun deleteItem(pos: Int){
        println("-----------" + items.toString())
        items.drop(pos)
        this.notifyDataSetChanged()
        println(items.toString())

    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun submitList(itemList : List<Item>){
        items = itemList
    }

    class ItemViewHolder constructor(
        itemView:View
    ):RecyclerView.ViewHolder(itemView){
        val item_image = itemView.img_product
        val price = itemView.calc_price
        val name = itemView.txt_product_name
        var amount = itemView.amount
        var btnDelete = itemView.delete

        fun bind(item: Item){
            btnDelete.setOnClickListener {
                layoutPosition.also { currentPosition ->
                        var i: ItemRecyclerAdapter = ItemRecyclerAdapter()
                            i.deleteItem(currentPosition)
                }

            }
            item.totalPrice = item.price!!.toInt()
            item.amount = 1
            amount.setOnValueChangeListener { view, oldValue, newValue ->
                val new :Int = newValue
                if (item.amount?.toInt() ?: 0 > new) {
                    item.totalPrice = item.totalPrice!! - item.price!!.toInt()
                } else
                    item.totalPrice = item.totalPrice!! + item.price!!.toInt()
                println("AICI ESTE PRETUL TOTAL BOSS")
                println(item.totalPrice!!)
                item.amount = new
                val p = new * (item.price?.toInt() ?:1)
                price.setText(p.toString())

            }
            println("CANTITAAAAAAATE ")
            println(item.amount!!)
            name.setText(item.name)
            price.setText(item.price)



            val requestOption = RequestOptions()
                .placeholder(R.drawable.fifi)
                .error(R.drawable.fifi)
            if(item.name.equals("Scented Lavender")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f4)
                    .into(item_image)
            }
            else if(item.name.equals("Simply Pink")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f2)
                    .into(item_image)
            }else if(item.name.equals("Rose bud")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f3)
                    .into(item_image)
            }else if(item.name.equals("Peonies")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f1)
                    .into(item_image)
            }else if(item.name.equals("Romantic")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f5)
                    .into(item_image)
            }else if(item.name.equals("Blue Bell")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f6)
                    .into(item_image)
            }else if(item.name.equals("Blues")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f7)
                    .into(item_image)
            }else if(item.name.equals("Casa Blanca")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f8)
                    .into(item_image)
            }else if(item.name.equals("Citrus")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f9)
                    .into(item_image)
            }else if(item.name.equals("Glaze")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f10)
                    .into(item_image)
            }else if(item.name.equals("Godess")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f11)
                    .into(item_image)
            }else if(item.name.equals("Purity")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f12)
                    .into(item_image)
            }else if(item.name.equals("Snapdragons")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f13)
                    .into(item_image)
            }else if(item.name.equals("Scented Spring")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f14)
                    .into(item_image)
            }else if(item.name.equals("Secret Garden")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f15)
                    .into(item_image)
            }else if(item.name.equals("Star Gazer")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f16)
                    .into(item_image)
            }else if(item.name.equals("Topaz")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f17)
                    .into(item_image)
            }else if(item.name.equals("Tulips")) {
                Glide.with(itemView.context)
                    .load(R.drawable.f18)
                    .into(item_image)
            }
        }

    }
}
