package com.example.fifi.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fifi.model.Client
import com.example.fifi.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // connection to the home page
        val btnHome: ImageButton = findViewById(R.id.homeButton)
        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // connection to the custom bouquet page
        val btncustom: ImageButton = findViewById(R.id.customBouquet)
        btncustom.setOnClickListener {
            val intent = Intent(this, BouquetActivity::class.java)
            startActivity(intent)
        }

        // sign out the client
        val btnout: Button = findViewById(R.id.SignOut)
        btnout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val reset: Button = findViewById(R.id.reset)
        reset.setOnClickListener{
            reset.visibility = View.INVISIBLE
            feedbackButton.visibility = View.INVISIBLE
            feedbackHeadText.visibility = View.INVISIBLE
            feedbackText.visibility = View.INVISIBLE
            sendFeedbackButton.visibility = View.INVISIBLE

            // dynamical/y the edit texts fot the password change

            //username fields
            val username = EditText(this)
            username.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param = username.layoutParams as RelativeLayout.LayoutParams
            param.topMargin = 1060
            param.leftMargin = 360
            username.hint = "Username"
            username.setHintTextColor(Color.BLACK)
            username.setTextColor(Color.BLACK)
            layoutReset?.addView(username)

            // old password field
            val oldPass = EditText(this)
            oldPass.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param1 = oldPass.layoutParams as RelativeLayout.LayoutParams
            param1.setMargins(330,1160,0,0)
            oldPass.hint = "Old password"
            oldPass.setHintTextColor(Color.BLACK)
            oldPass.setTextColor(Color.BLACK)
            oldPass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            layoutReset?.addView(oldPass)

            // new password field
            val newPass = EditText(this)
            newPass.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param2 = newPass.layoutParams as RelativeLayout.LayoutParams
            param2.setMargins(320,1260,0,0)
            newPass.hint = "New password"
            newPass.setHintTextColor(Color.BLACK)
            newPass.setTextColor(Color.BLACK)
            newPass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            layoutReset?.addView(newPass)

            // confirm password field
            val confirmpass = EditText(this)
            confirmpass.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param3 = confirmpass.layoutParams as RelativeLayout.LayoutParams
            param3.setMargins(300,1360,0,0)
            confirmpass.hint = "Confirm password"
            confirmpass.setHintTextColor(Color.BLACK)
            confirmpass.setTextColor(Color.BLACK)
            confirmpass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            layoutReset?.addView(confirmpass)

            // change the password
            val ok = Button(this)
            ok.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param4 = ok.layoutParams as RelativeLayout.LayoutParams
            param4.setMargins(350,1560,200,0)
            ok.hint = "Reset"
            ok.setHintTextColor(Color.BLACK)
            ok.setBackgroundResource(R.drawable.button2_background)
            layoutReset?.addView(ok)


            ok.setOnClickListener{
                feedbackButton.visibility = View.VISIBLE
                reset.visibility = View.VISIBLE
                ok.visibility = View.INVISIBLE
                confirmpass.visibility = View.INVISIBLE
                newPass.visibility = View.INVISIBLE
                oldPass.visibility = View.INVISIBLE
                username.visibility = View.INVISIBLE

                // connection to the database
                val databaseReference = FirebaseDatabase.getInstance().getReference("clients")

                val query: Query = databaseReference.orderByChild("username").equalTo(username.text.toString().trim())

                // find the user and change the password
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (client in dataSnapshot.children) {
                                val usersBean: Client = client.getValue<Client>(
                                    Client::class.java) ?: throw IllegalArgumentException("Name required")
                                if (usersBean.password == oldPass.text.toString().trim()) {
                                    if(newPass.text.toString() == confirmpass.text.toString()){
                                            val newPass1 = newPass.text.toString().trim()
                                            val cli =
                                                Client(
                                                    usersBean.id,
                                                    usersBean.firstName,
                                                    usersBean.lastName,
                                                    usersBean.username,
                                                    newPass1,
                                                    usersBean.email
                                                )
                                            databaseReference.child(usersBean.id).setValue(cli)
                                        Toast.makeText(applicationContext, "Password changed !", Toast.LENGTH_LONG).show()
                                    }

                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        "Old assword is wrong",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        } else {
                            Toast.makeText(applicationContext, "User not found", Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onCancelled(databaseError: DatabaseError) {}
                })

            }

        }

        // create the feedback edit text
        val feedbackButton : Button = findViewById(R.id.feedbackButton)
        val send : Button = findViewById(R.id.sendFeedbackButton)
        val feedbackText : EditText = findViewById(R.id.feedbackText)
        val textHead : TextView = findViewById(R.id.feedbackHeadText)
        feedbackText.visibility = View.INVISIBLE
        feedbackHeadText.visibility = View.INVISIBLE

        feedbackButton.setOnClickListener {

            feedbackButton.visibility = View.INVISIBLE
            feedbackText.visibility = View.VISIBLE
            feedbackHeadText.visibility = View.VISIBLE
            send.visibility = View.VISIBLE

        }

        send.setOnClickListener {
            var text = ""
            text = feedbackText.text.toString().trim()

            val ref = FirebaseDatabase.getInstance().getReference("feedbacks")

            // push the feedback into the database
            if(text == ""){
                Toast.makeText(applicationContext, "Feedback null", Toast.LENGTH_LONG).show()
            } else {
                val feedbackId = ref.push().key
                ref.child(feedbackId.toString()).setValue(text)
                Toast.makeText(applicationContext, "Your feedback has been recorded!", Toast.LENGTH_LONG).show()
            }

            send.visibility = View.INVISIBLE
            text = ""
            feedbackText.text = null
            feedbackText.visibility = View.INVISIBLE
            feedbackHeadText.visibility = View.INVISIBLE
            feedbackButton.visibility = View.VISIBLE
        }
    }
}


