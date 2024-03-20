package com.example.cluckingacres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore


private val Nothing?.text: Any
    get() {
        TODO("Not yet implemented")
    }
private val Nothing?.selectedItemPosition: Int
    get() {
        TODO("Not yet implemented")
    }

class FeedsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }
}
class FeedsFragment1 : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var feedTypeSpinner: Spinner
    private lateinit var quantityEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_feeds)

        // Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Initialize UI components
        feedTypeSpinner = findViewById(R.id.feedTypeSpinner)
        quantityEditText = findViewById(R.id.quantityEditText)

        val recordFeedButton: Button = findViewById(R.id.recordButton)
        recordFeedButton.setOnClickListener {
            // Get feed type and quantity from UI components
            val selectedFeedTypePosition = feedTypeSpinner.selectedItemPosition
            val selectedFeedType = resources.getStringArray(R.array.feed_types)[selectedFeedTypePosition]
            val feedQuantity = quantityEditText.text.toString().toInt()

            // Create a map to represent the feed data
            val feedData = hashMapOf(
                "type" to selectedFeedType,
                "quantity" to feedQuantity
            )

            // Add the feed data to the "feeds" collection in Firestore
            db.collection("feeds")
                .add(feedData)
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