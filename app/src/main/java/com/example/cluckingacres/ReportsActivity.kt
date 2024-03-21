package com.example.cluckingacres

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ReportsActivity : AppCompatActivity() {

    private lateinit var dailyEggCountTextView: TextView
    private lateinit var dailyFeedsGivenTextView: TextView
    private lateinit var dailyVaccinationsGivenTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_reports)

        // Initialize TextViews
        dailyEggCountTextView = findViewById(R.id.textViewDailyEggCount)
        dailyFeedsGivenTextView = findViewById(R.id.textViewDailyFeedsGiven)
        dailyVaccinationsGivenTextView = findViewById(R.id.textViewDailyVaccinationsGiven)

        // Fetch data from Firestore
        fetchReportData()
    }

    private fun fetchReportData() {
        // Firestore instance
        val firestore = FirebaseFirestore.getInstance()

        // Fetch daily egg count
        firestore.collection("Eggsreport")
            .get()
            .addOnSuccessListener { result ->
                var eggCount = 0
                for (document in result) {
                    // Sum up egg count from documents
                    eggCount += document.get("eggCount") as? Int ?: 0
                }
                dailyEggCountTextView.text = "Eggs Harvested: $eggCount"
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        // Fetch daily feeds given
        firestore.collection("feeds")
            .get()
            .addOnSuccessListener { result ->
                var feedsGiven = ""
                for (document in result) {
                    // Append feed item to the string
                    feedsGiven += "${document.get("type")} (${document.get("quantity")}), "
                }
                // Remove trailing comma and space
                feedsGiven = feedsGiven.trimEnd(',', ' ')
                dailyFeedsGivenTextView.text = "Feeds Given: $feedsGiven"
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        // Fetch daily vaccinations given
        firestore.collection("Vaccinations")
            .get()
            .addOnSuccessListener { result ->
                var vaccinationsGiven = ""
                for (document in result) {
                    // Append vaccination item to the string
                    vaccinationsGiven += "${document.get("vacccinationDate")} (${document.get("VaccinationType")}), "
                }
                // Remove trailing comma and space
                vaccinationsGiven = vaccinationsGiven.trimEnd(',', ' ')
                dailyVaccinationsGivenTextView.text = "Vaccinations Given: $vaccinationsGiven"
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    companion object {
        private const val TAG = "ReportActivity"
    }
}
