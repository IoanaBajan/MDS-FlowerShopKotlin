package com.example.test

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_placeorder.*
import kotlin.math.max


class PlaceOrderActivity: AppCompatActivity() {
    lateinit var card: RadioButton
    lateinit var placeOrder: Button

    lateinit var idfullname: EditText
    lateinit var idphonenumber: EditText
    lateinit var idaddress: EditText
    lateinit var idradiogroup: RadioGroup
    var maxid = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeorder)

        card = findViewById(R.id.idCard)
        placeOrder = findViewById(R.id.idPlaceOrder)

        idfullname = findViewById(R.id.idFullName)
        idphonenumber = findViewById(R.id.idPhoneNumber)
        idaddress = findViewById(R.id.idAddress)
        idradiogroup = findViewById(R.id.radioGroup)

        card.setOnClickListener {
            cardDetails()
        }

        placeOrder.setOnClickListener{
//            val recipient = "alinamocanu2@gmail.com".trim()
////            val subject = "Comanda dumneavoastra!".trim()
////            val message = "Comanda a fost plasata !".trim()
////            sendEmail(recipient, subject, message)
            saveOrder()
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


        val ref = FirebaseDatabase.getInstance().getReference("orders")


        val orderId = ref.push().key

        val order = Order(orderId.toString(), fullname, address, phonenumber,paymentmethod)

        ref.child(orderId.toString()).setValue(order).addOnCompleteListener(){
            Toast.makeText(applicationContext, "Succes!", Toast.LENGTH_LONG).show()
        }
    }

    private fun cardDetails() {
        if (card.isChecked) {
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