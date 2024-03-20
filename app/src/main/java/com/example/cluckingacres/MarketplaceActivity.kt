package com.example.cluckingacres

import PaymentResponse
import RetrofitClient
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketplaceActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_marketplace)

        // Replace the fragmentContainer with the MarketplaceFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MarketplaceFragment())
            .commit()
        val layoutSeller: LinearLayout = findViewById(R.id.layoutSeller)
        val layoutBuyer: LinearLayout = findViewById(R.id.layoutBuyer)
        val btnSwitchView: Button = findViewById(R.id.btnSwitchView)

        // Initial state: Show Seller View, Hide Buyer View
        layoutSeller.visibility = View.VISIBLE
        layoutBuyer.visibility = View.GONE

        btnSwitchView.setOnClickListener {
            // Switch between Seller and Buyer views
            if (layoutSeller.visibility == View.VISIBLE) {
                layoutSeller.visibility = View.GONE
                layoutBuyer.visibility = View.VISIBLE
                btnSwitchView.text = "Switch to Seller View"
            } else {
                layoutSeller.visibility = View.VISIBLE
                layoutBuyer.visibility = View.GONE
                btnSwitchView.text = "Switch to Buyer View"
            }
        }

        // Seller functionality
        val btnUploadEggs: Button = findViewById(R.id.btnUploadEggs)
        btnUploadEggs.setOnClickListener {
            // Handle the upload eggs logic here
            // Get the number of eggs from etNumberOfEggs
            // Upload the number of eggs to the marketplace
        }

        // Buyer functionality
        val btnBuyEggs: Button = findViewById(R.id.button)
        btnBuyEggs.setOnClickListener {
            // Handle the buy eggs logic here
            // Get the number of eggs to buy from etNumberOfEggsToBuy
            // Get the phone number from etPhoneNumber
            // Get the name from etName
            // Display Mpesa prompt for payment
            initiatePayment()
        }
    }

    private val darajaService = RetrofitClient.instance

    // Function to initiate payment
    private fun initiatePayment() {
        val call = darajaService.makePayment()

        call.enqueue(object : Callback<PaymentResponse> {
            override fun onResponse(call: Call<PaymentResponse>, response: Response<PaymentResponse>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    val paymentResponse = response.body()
                    // Process payment response
                } else {
                    // Handle unsuccessful response
                    // Maybe show an error message to the user
                }
            }

            override fun onFailure(call: Call<PaymentResponse>, t: Throwable) {
                // Handle failure
                // Maybe show an error message to the user
            }
        })
    }
}
