package com.example.cluckingacres

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SignupActivity : AppCompatActivity() {
    private lateinit var emailInput: EditText
    private lateinit var userNameInput: EditText
    private lateinit var phoneNumberInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var userNameInputLayout: TextInputLayout
    private lateinit var phoneNumberInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var confirmPasswordInputLayout: TextInputLayout
    private lateinit var signUpButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        emailInput = findViewById(R.id.email)
        userNameInput = findViewById(R.id.username)
        phoneNumberInput = findViewById(R.id.phone)
        passwordInput = findViewById(R.id.password)
        confirmPasswordInput = findViewById(R.id.Confirm)
        emailInputLayout = findViewById(R.id.emailInputLayout)
        userNameInputLayout = findViewById(R.id.usernameInputLayout)
        phoneNumberInputLayout = findViewById(R.id.phoneNumberInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        confirmPasswordInputLayout = findViewById(R.id.confirmPasswordInputLayout)
        signUpButton = findViewById(R.id.btnSignup)

        signUpButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val userName = userNameInput.text.toString().trim()
            val phoneNumber = phoneNumberInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = confirmPasswordInput.text.toString().trim()


            if (email.isEmpty()) {
                emailInputLayout.error = "Email is required"
            } else {
                emailInputLayout.error = null
            }

            if (userName.isEmpty()) {
                userNameInputLayout.error = "First name is required"
            } else {
                userNameInputLayout.error = null
            }

            if (phoneNumber.isEmpty()) {
                phoneNumberInputLayout.error = "phone number is required"
            } else {
                phoneNumberInputLayout.error = null
            }

            if (password.isEmpty()) {
                passwordInputLayout.error = "Password is required"
            } else {
                passwordInputLayout.error = null
            }

            if (confirmPassword.isEmpty()) {
                confirmPasswordInputLayout.error = "Please confirm your password"
            } else {
                confirmPasswordInputLayout.error = null
            }

            if (password != confirmPassword) {
                confirmPasswordInputLayout.error = "Passwords do not match"
            } else {
                confirmPasswordInputLayout.error = null
            }
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val firebaseUser = auth.currentUser
                            val uid = firebaseUser?.uid ?: ""
                            val user = hashMapOf(
                                "uid" to uid,
                                "email" to email,
                                "userName" to userName,
                                "phoneNumber" to phoneNumber
                            )

                            db.collection("users")
                                .document(uid) // Use the UID as document ID
                                .set(user)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "DocumentSnapshot added with ID: $uid")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }

                            // Sign in success, update UI with the signed-in user's information
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

            } else {
                Toast.makeText(
                    this,
                    "Email and password fields cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()

                // Create Intent object
            }
            val loginButton = findViewById<Button>(R.id.btnlogin)

            loginButton.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }
    }
}



