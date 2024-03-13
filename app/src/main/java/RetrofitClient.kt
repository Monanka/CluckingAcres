import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.safaricom.com/"

    // Replace these placeholders with your actual consumer key and consumer secret
    private const val CONSUMER_KEY = "o20VNwp1e9AIZ5LOYGBYcOEOQlnxEAAvkSP0d7zOTVSfJufh"
    private const val CONSUMER_SECRET = "5AmowqXp8AcPxc6zMuydIvDddrV6OVvAFChDhuyqbmAWHUIZ0QDR8aAPa8X9Uk9v"

    val instance: DarajaService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val credential = Credentials.basic(CONSUMER_KEY, CONSUMER_SECRET)
                val request = chain.request()
                    .newBuilder()
                    .header("Authorization", credential)
                    .build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(DarajaService::class.java)
    }
}
