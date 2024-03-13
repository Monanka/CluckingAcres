
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

// Define data classes for request and response bodies if needed
data class PaymentRequest(
    val amount: Double,
    val phoneNumber: String,
    val reference: String
)

data class PaymentResponse(
    val transactionId: String,
    val status: String
)

// Define the API interface using Retrofit annotations
interface DarajaService {

    // Example endpoint for initiating a payment
    @POST("https://sandbox.safaricom.co.ke/mpesa/b2c/v3/paymentrequest")
    fun makePayment(): Call<PaymentResponse>
    @Headers("Content-Type: application/json")
    fun initiatePayment(
        @Header("Authorization") authHeader: String,  // Include authorization header with consumer key and secret
        @Body paymentRequest: PaymentRequest  // Include request body with payment details
    ): Call<PaymentResponse>

    // Endpoint for querying transaction status
    @GET("transactions/{transactionId}")
    @Headers("Content-Type: application/json")
    fun getTransactionStatus(
        @Header("Authorization") authHeader: String,  // Include authorization header with consumer key and secret
        @Path("transactionId") transactionId: String  // Include transaction ID in the endpoint path
    ): Call<TransactionStatusResponse>

    abstract fun create(java: Class<DarajaService>): Any
}

class TransactionStatusResponse {

}
