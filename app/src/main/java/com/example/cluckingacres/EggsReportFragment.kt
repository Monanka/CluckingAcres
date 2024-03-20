package com.example.cluckingacres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class EggsReportFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eggsreport, container, false)
    }
}
class EggsReportFragment1 : AppCompatActivity() {
    private lateinit var editTextEggCount: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_eggsreport)

        // Initialize Firestore
        db = FirebaseFirestore.getInstance()
        editTextEggCount = findViewById(R.id.editTextEggCount)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        val EggsrepportButton: Button = findViewById(R.id.buttonSubmit)
        EggsrepportButton.setOnClickListener {

            val eggCount = editTextEggCount.text.toString().toIntOrNull()

            // Save egg count to Firestore
            val eggReport = hashMapOf(
                "eggCount" to eggCount
            )

            db.collection("EggsReport") // Change collection name to "EggsReport"
                .add(eggReport)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(
                        baseContext, "Successfully added.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        baseContext, "Adding failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}

