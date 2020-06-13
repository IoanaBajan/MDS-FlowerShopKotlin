package com.example.fifi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fifi.model.Client
import com.example.fifi.R
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    lateinit var idUsernameR: EditText
    lateinit var idPasswordR: EditText
    lateinit var idEmail: EditText
    lateinit var idLastName: EditText
    lateinit var idFirstName: EditText
    lateinit var idRegister: Button
    lateinit var idConfirm: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        idUsernameR = findViewById(R.id.idUsernameR)
        idPasswordR = findViewById(R.id.idPasswordR)
        idEmail = findViewById(R.id.idEmail)
        idLastName = findViewById(R.id.idLastName)
        idFirstName = findViewById(R.id.idFirstName)
        idRegister = findViewById(R.id.idRegister)
        idConfirm = findViewById(R.id.idConfirmPasswordR)

        idRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity:: class.java)
            saveClient(intent)
        }
    }

    private fun saveClient(intent: Intent){
        val firstName = idFirstName.text.toString().trim()
        val lastName = idLastName.text.toString().trim()
        val username = idUsernameR.text.toString().trim()
        val password = idPasswordR.text.toString().trim()
        val confirm = idConfirm.text.toString().trim()
        val mail = idEmail.text.toString().trim();

        val ref = FirebaseDatabase.getInstance().getReference("clients")

        if(password.equals(confirm)) {
            if(password.length >= 4){
                if(mail.contains("@")) {
                    val clientId = ref.push().key

                    val client = Client(
                        clientId.toString(),
                        firstName,
                        lastName,
                        username,
                        password,
                        mail
                    )

                    ref.child(clientId.toString()).setValue(client).addOnCompleteListener() {
                        Toast.makeText(applicationContext, "Succes!", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    Toast.makeText(applicationContext, "Not a valid email address", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(applicationContext, "Password shoul have minimum 4 characters", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(applicationContext, "Passwords don't match", Toast.LENGTH_LONG).show()
        }

    }
}