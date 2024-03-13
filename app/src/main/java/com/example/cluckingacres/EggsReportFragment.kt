package com.example.cluckingacres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class EggsReportFragment : Fragment() {

    private lateinit var editTextEggCount: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_eggsreport, container, false)
        editTextEggCount = view.findViewById(R.id.editTextEggCount)
        buttonSubmit = view.findViewById(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            submitEggReport()
        }

        return view
    }

    private fun submitEggReport() {
        val eggCount = editTextEggCount.text.toString().toIntOrNull()
        if (eggCount != null) {
            // Save egg count to database or perform necessary actions
            // You can use ViewModel or Repository to handle data operations
            // For demonstration, let's just print the count for now
            println("Daily Egg Count: $eggCount")
        } else {
            // Handle invalid input
            editTextEggCount.error = "Please enter a valid number"
        }
    }
}
