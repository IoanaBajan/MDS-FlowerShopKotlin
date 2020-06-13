package com.example.fifi.activities

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
    lateinit var card: RadioButton
    lateinit var placeOrder: Button
    lateinit var idfullname: EditText
    lateinit var idphonenumber: EditText
    lateinit var idaddress: EditText
    lateinit var idradiogroup: RadioGroup
    lateinit var idmessage: EditText
    lateinit var datePicker: DatePicker
    lateinit var showCost :TextView
    lateinit var showComanda:TextView
    var comanda :String = ""
    var totalCost :Int = 0
    var ok:Boolean = false

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
        if (intent != null) {
            itemList = intent.getSerializableExtra("itemList") as ArrayList<Item>

        }
        for (item in itemList) {
            totalCost += item.totalPrice!!.toInt()
            comanda +=item.name + " " + item.amount + "\n"
        }

        card.setOnClickListener {
            cardDetails()
        }

        showCost = findViewById(R.id.cost)
        showCost.setText("Total price :" + totalCost+ "lei")

        showComanda = findViewById(R.id.comanda)
        showComanda.setText(comanda)

        val param = showComanda.layoutParams as LinearLayout.LayoutParams
        param.height = itemList.size*90


        placeOrder.setOnClickListener{
            //            val recipient = "alinamocanu2@gmail.com".trim()
//            val subject = "Comanda dumneavoastra!".trim()
//           val message = "Comanda a fost plasata !".trim()
//           sendEmail(recipient, subject, message)
            saveOrder()
            val intent = Intent(this@PlaceOrderActivity, OrderConfirmationActivity::class.java)
            startActivity(intent)
        }
    }

//    private fun sendEmail(recipient:String, subject: String, message: String) {
//        val mIntent = Intent(Intent.ACTION_SEND)
//        mIntent.data = Uri.parse("mailto:")
//        mIntent.type = "text/plain"
//        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
//        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
//        mIntent.putExtra(Intent.EXTRA_TEXT, message)
//
//        try {
//            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
//        }
//        catch (e: Exception){
//            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
//        }
//}

    private fun saveOrder(){
        val fullname = idfullname.text.toString().trim()
        val address = idaddress.text.toString().trim();
        val phonenumber = idphonenumber.text.toString().trim();
        val payment = idradiogroup.checkedRadioButtonId
        val paymentmethod = resources.getResourceEntryName(payment)
        val price = showCost.text.toString().trim()
        val comanda = showComanda.text.toString().trim()

        val message = idmessage.text.toString().trim()

        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1
        val year = datePicker.year
        var date = day.toString()+ "/" + month.toString() + "/" + year.toString()
        date = date.trim()
        val ref = FirebaseDatabase.getInstance().getReference("orders")

        if(fullname.length != 0) {
            if(address.length != 0){
                if(phonenumber.length != 0 && phonenumber.contains("07")){
                    val orderId = ref.push().key

                    val order = Order
                    Order.id = orderId.toString()
                    Order.fullname = fullname
                    Order.address = address
                    Order.phonenumber = phonenumber
                    Order.payment = paymentmethod
                    Order.message = message
                    Order.date = date
                    Order.price = price
                    Order.command = comanda


                    ref.child(orderId.toString()).setValue(order).addOnCompleteListener() {
                        Toast.makeText(applicationContext, "Succes!", Toast.LENGTH_LONG).show()
                    }
                }
                else Toast.makeText(applicationContext, "Not a valid phone number", Toast.LENGTH_LONG).show()
            }
            else Toast.makeText(applicationContext, "Not a valid address", Toast.LENGTH_LONG).show()
        }
        else Toast.makeText(applicationContext, "Name must not be null", Toast.LENGTH_LONG).show()
    }

    private fun cardDetails() {
        if (card.isChecked && !ok) {
            ok = true
            val nrCard = EditText(this)
            nrCard.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            val maxLength1 = 16
            nrCard.setFilters(arrayOf<InputFilter>(LengthFilter(maxLength1)))

            val param = nrCard.layoutParams as LinearLayout.LayoutParams
            param.setMargins(80,0,0,10)


            nrCard.setHint("Numar card")
            layoutCard?.addView(nrCard)

            val cvv= EditText(this)
            cvv.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            val maxLength = 3
            cvv.setFilters(arrayOf<InputFilter>(LengthFilter(maxLength)))

            val param1 = cvv.layoutParams as LinearLayout.LayoutParams
            param1.setMargins(80,20,0,10)

            cvv.setHint("CVV")
            layoutCard?.addView(cvv)
        }
    }
}