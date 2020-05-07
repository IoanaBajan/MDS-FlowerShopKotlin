package com.example.test

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.idUsername)
        password = findViewById(R.id.idPassword)

        val btnOpenActivity: Button = findViewById(R.id.idSignIn)
        btnOpenActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            signin(intent)
        }

        val btnOpenActivity1: Button = findViewById(R.id.idRegister)
        btnOpenActivity1.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    private fun signin(intent: Intent) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("clients")

        val query: Query = databaseReference.orderByChild("username").equalTo(username.getText().toString().trim())

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    var client:DataSnapshot
                    for (client in dataSnapshot.children) {
                        val usersBean: Client = client.getValue<Client>(Client::class.java) ?: throw IllegalArgumentException("Name required")
                            if (usersBean.password.equals(password.getText().toString().trim())) {
                                Toast.makeText(
                                    applicationContext,
                                    "Succes login!",
                                    Toast.LENGTH_LONG
                                ).show()
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
