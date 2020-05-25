package com.example.test

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_placeorder.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import org.w3c.dom.Text

class ProfileActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnHome: ImageButton = findViewById(R.id.homeButton)
        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val btncustom: ImageButton = findViewById(R.id.customBouquet)
        btncustom.setOnClickListener {
            val intent = Intent(this, BouquetActivity::class.java)
            startActivity(intent)
        }

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

            val username = EditText(this)
            username.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param = username.layoutParams as RelativeLayout.LayoutParams
            param.topMargin = 1060
            param.leftMargin = 360
            username.setHint("Username")
            username.setHintTextColor(Color.BLACK)
            username.setTextColor(Color.BLACK)
            layoutReset?.addView(username)


            val oldpass = EditText(this)
            oldpass.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param1 = oldpass.layoutParams as RelativeLayout.LayoutParams
            param1.setMargins(330,1160,0,0)
            oldpass.setHint("Old password")
            oldpass.setHintTextColor(Color.BLACK)
            oldpass.setTextColor(Color.BLACK)
            oldpass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            layoutReset?.addView(oldpass)


            val newpass = EditText(this)
            newpass.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param2 = newpass.layoutParams as RelativeLayout.LayoutParams
            param2.setMargins(320,1260,0,0)
            newpass.setHint("New password")
            newpass.setHintTextColor(Color.BLACK)
            newpass.setTextColor(Color.BLACK)
            newpass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            layoutReset?.addView(newpass)


            val confirmpass = EditText(this)
            confirmpass.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param3 = confirmpass.layoutParams as RelativeLayout.LayoutParams
            param3.setMargins(300,1360,0,0)
            confirmpass.setHint("Confirm password")
            confirmpass.setHintTextColor(Color.BLACK)
            confirmpass.setTextColor(Color.BLACK)
            confirmpass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            layoutReset?.addView(confirmpass)


            val ok = Button(this)
            ok.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val param4 = ok.layoutParams as RelativeLayout.LayoutParams
            param4.setMargins(350,1560,200,0)
            ok.setHint("Reset")
            ok.setHintTextColor(Color.BLACK)
            ok.setBackgroundResource(R.drawable.button2_background)
            layoutReset?.addView(ok)


            ok.setOnClickListener{
                feedbackButton.visibility = View.VISIBLE
                reset.visibility = View.VISIBLE
                ok.visibility = View.INVISIBLE
                confirmpass.visibility = View.INVISIBLE
                newpass.visibility = View.INVISIBLE
                oldpass.visibility = View.INVISIBLE
                username.visibility = View.INVISIBLE


                val databaseReference = FirebaseDatabase.getInstance().getReference("clients")

                val query: Query = databaseReference.orderByChild("username").equalTo(username.getText().toString().trim())

                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (client in dataSnapshot.children) {
                                val usersBean: Client = client.getValue<Client>(Client::class.java) ?: throw IllegalArgumentException("Name required")
                                if (usersBean.password.equals(oldpass.getText().toString().trim())) {
                                    if(newpass.getText().toString().equals(confirmpass.getText().toString())){
                                            var newpass1 = newpass.getText().toString().trim()
                                            val cli = Client(usersBean.id, usersBean.firstName, usersBean.lastName, usersBean.username, newpass1, usersBean.email)
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

            if(text.equals("")){
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


