package com.example.learningofdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.learningofdatabase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private  lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {

            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val phoneNumber = binding.phoneNumber.text.toString()
            val userName = binding.userName.text.toString()

            if (firstName.isEmpty()){
                binding.firstName.error = "Enter First Name"
            }

            if (lastName.isEmpty()){
                binding.lastName.error = "Enter Last Name"
            }

            if (phoneNumber.isEmpty()){
                binding.phoneNumber.error = "Enter Phone Number"
            }

            if (userName.isEmpty()){
                binding.userName.error = "Enter Username"
            }
            database = FirebaseDatabase.getInstance().getReference("User")
            val user = User(firstName,lastName,phoneNumber,userName)
            database.child(userName).setValue(user).addOnSuccessListener {

                binding.firstName.text.clear()
                binding.lastName.text.clear()
                binding.phoneNumber.text.clear()
                binding.userName.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }



        }


    }
}