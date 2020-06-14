package com.example.fifi.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fifi.model.Client
import com.example.fifi.R
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private lateinit var username: EditText
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.idUsername)
        password = findViewById(R.id.idPassword)

        // connection to the home page
        val btnOpenActivity: Button = findViewById(R.id.idSignIn)
        btnOpenActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            signIn(intent)
        }

        // connection to the Sign Up page
        val btnOpenActivity1: Button = findViewById(R.id.idRegister)
        btnOpenActivity1.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    private fun signIn(intent: Intent) {
        // connection to the database
        val databaseReference = FirebaseDatabase.getInstance().getReference("clients")

        // find if the username typed by the user exists in the database
        val query: Query = databaseReference.orderByChild("username").equalTo(username.text.toString().trim())

        // verify is the password is correct
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (client in dataSnapshot.children) {
                        val usersBean: Client = client.getValue<Client>(
                            Client::class.java) ?: throw IllegalArgumentException("Name required")
                            if (usersBean.password == password.text.toString().trim()) {
                                Toast.makeText(
                                    applicationContext,
                                    "Succes login!",
                                    Toast.LENGTH_LONG
                                ).show()
                                // if the login was succeded it takes you to the home page
                                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Password is wrong",
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
