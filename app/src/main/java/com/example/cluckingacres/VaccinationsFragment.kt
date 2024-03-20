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


class VaccinationsFragment(selectedVaccinationType: String, vaccinationDate: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vaccinations, container, false)
    }
}
class VaccinationsFragment1 : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_vaccinations)
        val spinnerVaccinationType: Spinner = findViewById(R.id.spinnerVaccinationType)
        val editTextVaccinationDate: EditText = findViewById(R.id.editTextVaccinationDate)
        val buttonRecordVaccination: Button = findViewById(R.id.buttonRecordVaccination)

        buttonRecordVaccination.setOnClickListener {
            // Get the selected vaccination type and date
            val selectedVaccinationType = spinnerVaccinationType.selectedItem.toString()
            val vaccinationDate = editTextVaccinationDate.text.toString()

            // Create a map to represent the vaccination data
            val vaccinationData = hashMapOf(
                "vaccinationType" to selectedVaccinationType,
                "vaccinationDate" to vaccinationDate
            )

            // Access Firestore instance and add the vaccination data to the "Vaccinations" collection
            val db = FirebaseFirestore.getInstance()
            db.collection("Vaccinations")
                .add(vaccinationData)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(
                        baseContext, "Successfully added.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        baseContext, "Not added.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }


    }
}