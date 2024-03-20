package com.example.cluckingacres


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User

private val User?.phoneNumber: Any
    get() {
        TODO("Not yet implemented")
    }

class ProfileFragment : Fragment() {

    private lateinit var usernameTextView: TextView
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var updateButton: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        currentUser = mAuth.currentUser!!

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()

        // Initialize views
        usernameTextView = view.findViewById(R.id.Username)
        emailEditText = view.findViewById(R.id.edit_text_email)
        phoneEditText = view.findViewById(R.id.edit_text_phone)
        updateButton = view.findViewById(R.id.update_button)

        // Set username from Firebase User
        usernameTextView.text = currentUser.displayName

        // Set email from Firebase User
        emailEditText.setText(currentUser.email)

        // Retrieve user data from Firestore
        db.collection("users").document(currentUser.uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userData = document.toObject(User::class.java)
                    phoneEditText.setText(userData?.phoneNumber)
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
            }

        // Update user data on button click
        updateButton.setOnClickListener {
            val updatedPhone = phoneEditText.text.toString()

            val user = hashMapOf(
                "phoneNumber" to updatedPhone
            )

            db.collection("users").document(currentUser.uid)
                .set(user, SetOptions.merge())
                .addOnSuccessListener {
                    // Update successful
                }
                .addOnFailureListener { e ->
                    // Handle errors
                }
        }

        return view
    }
}

private fun EditText.setText(phoneNumber: Any?) {
    TODO("Not yet implemented")
}


class ProfileFragment1 : AppCompatActivity(){

    public override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile)
    }
}