package com.example.cluckingacres


import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var usernameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var phoneEditText: TextInputEditText
    private lateinit var updateButton: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile)

        mAuth = FirebaseAuth.getInstance()
        currentUser = mAuth.currentUser!!
        firestore = FirebaseFirestore.getInstance()

        profileImage = findViewById(R.id.profile_image)
        usernameEditText = findViewById(R.id.username)
        emailEditText = findViewById(R.id.edit_text_email)
        phoneEditText = findViewById(R.id.edit_text_phone)
        updateButton = findViewById(R.id.update_button)

        updateButton.setOnClickListener {
            val newUsername = usernameEditText.text.toString()
            val newPhoneNumber = phoneEditText.text.toString()

            // Update Firestore document
            val uid = mAuth.currentUser?.uid
            if (uid != null) {
                val userRef = firestore.collection("users").document(uid)
                userRef.update("new username", newUsername)
                    .addOnSuccessListener {
                        // Username updated successfully
                        Toast.makeText(this, "Username updated", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        // Handle failure
                        Toast.makeText(this, "Failed to update username: ${e.message}", Toast.LENGTH_SHORT).show()
                    }

                userRef.update("new phoneNumber", newPhoneNumber)
                    .addOnSuccessListener {
                        // Phone number updated successfully
                        Toast.makeText(this, "Phone number updated", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        // Handle failure
                        Toast.makeText(this, "Failed to update phone number: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // User is not signed in
                Toast.makeText(this, "User not signed in", Toast.LENGTH_SHORT).show()
            }
        }




        loadUserProfile()
    }

    private fun loadUserProfile() {
        val userId = currentUser.uid
        val userRef = firestore.collection("users").document(userId)

        userRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    val user = documentSnapshot.toObject(User::class.java)
                    if (user != null) {
                        // Load user data into UI components
                        usernameEditText.setText(user.userName)
                        emailEditText.setText(user.email)
                        phoneEditText.setText(user.phoneNumber)
                    }
                }

            }
            .addOnFailureListener { exception ->
                // Handle failures
            }
    }

}

class User {
    val userName: String = ""
    val email: String = ""
    val phoneNumber: String = ""

}
