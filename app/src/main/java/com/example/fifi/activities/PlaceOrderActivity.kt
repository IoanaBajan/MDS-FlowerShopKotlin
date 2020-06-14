package com.example.fifi.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fifi.model.Item
import com.example.fifi.model.Order
import com.example.fifi.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_placeorder.*


class PlaceOrderActivity: AppCompatActivity() {
    private lateinit var card: RadioButton
    private lateinit var placeOrder: Button
    private lateinit var idfullname: EditText
    private lateinit var idphonenumber: EditText
    private lateinit var idaddress: EditText
    private lateinit var idradiogroup: RadioGroup
    private lateinit var idmessage: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var showCost :TextView
    private lateinit var showComanda:TextView
    private var comanda :String = ""
    private var totalCost :Int = 0
    private var ok:Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        var itemList: ArrayList<Item> = ArrayList()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeorder)

        card = findViewById(R.id.Card)
        placeOrder = findViewById(R.id.idPlaceOrder)

        idfullname = findViewById(R.id.idFullName)
        idphonenumber = findViewById(R.id.idPhoneNumber)
        idaddress = findViewById(R.id.idAddress)
        idradiogroup = findViewById(R.id.radioGroup)
        idmessage = findViewById(R.id.idMessage)
        datePicker= findViewById(R.id.idDatePicker)

        // get items from the shopping cart to display order summary
        if (intent != null) {
            itemList = intent.getSerializableExtra("itemList") as ArrayList<Item>
        }

        // compute total price
        for (item in itemList) {
            totalCost += item.totalPrice!!.toInt()
            comanda +=item.name + " " + item.amount + "\n"
        }

        // when card payment is selected, display further info such as cvv and card number
        card.setOnClickListener {
            cardDetails()
        }

        showCost = findViewById(R.id.cost)
        showCost.text = "Total price :" + totalCost + "lei"

        showComanda = findViewById(R.id.comanda)
        showComanda.text = comanda

        val param = showComanda.layoutParams as LinearLayout.LayoutParams
        param.height = itemList.size*90


        placeOrder.setOnClickListener{
            saveOrder()
            val intent = Intent(this@PlaceOrderActivity, OrderConfirmationActivity::class.java)
            startActivity(intent)
        }
    }

    // add order to database
    private fun saveOrder(){
        val fullName = idfullname.text.toString().trim()
        val address = idaddress.text.toString().trim()
        val phoneNumber = idphonenumber.text.toString().trim()
        val payment = idradiogroup.checkedRadioButtonId
        val paymentMethod = resources.getResourceEntryName(payment)
        val price = showCost.text.toString().trim()
        val comanda = showComanda.text.toString().trim()

        val message = idmessage.text.toString().trim()

        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1
        val year = datePicker.year
        var date = "$day/$month/$year"
        date = date.trim()
        val ref = FirebaseDatabase.getInstance().getReference("orders")

        // all fields must be filled
        if(fullName.isNotEmpty()) {
            if(address.isNotEmpty()){
                if(phoneNumber.isNotEmpty() && phoneNumber.contains("07")){
                    val orderId = ref.push().key

                    val order = Order
                    Order.id = orderId.toString()
                    Order.fullname = fullName
                    Order.address = address
                    Order.phonenumber = phoneNumber
                    Order.payment = paymentMethod
                    Order.message = message
                    Order.date = date
                    Order.price = price
                    Order.command = comanda

                    // display toast message to notify user the form is completed correctly
                    ref.child(orderId.toString()).setValue(order).addOnCompleteListener {
                        Toast.makeText(applicationContext, "Succes!", Toast.LENGTH_LONG).show()
                    }
                }
                else Toast.makeText(applicationContext, "Not a valid phone number", Toast.LENGTH_LONG).show()
            }
            else Toast.makeText(applicationContext, "Not a valid address", Toast.LENGTH_LONG).show()
        }
        else Toast.makeText(applicationContext, "Name must not be null", Toast.LENGTH_LONG).show()
    }

    // display all necessary info for card payment when it is selected
    private fun cardDetails() {
        if (card.isChecked && !ok) {
            ok = true
            val nrCard = EditText(this)
            nrCard.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            val maxLength1 = 16
            nrCard.filters = arrayOf<InputFilter>(LengthFilter(maxLength1))

            val param = nrCard.layoutParams as LinearLayout.LayoutParams
            param.setMargins(80,0,0,10)


            nrCard.hint = "Numar card"
            layoutCard?.addView(nrCard)

            val cvv= EditText(this)
            cvv.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            val maxLength = 3
            cvv.filters = arrayOf<InputFilter>(LengthFilter(maxLength))

            val param1 = cvv.layoutParams as LinearLayout.LayoutParams
            param1.setMargins(80,20,0,10)

            cvv.hint = "CVV"
            layoutCard?.addView(cvv)
        }
    }
}