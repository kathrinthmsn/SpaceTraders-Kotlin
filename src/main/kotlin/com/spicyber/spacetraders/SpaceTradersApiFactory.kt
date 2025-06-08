package com.spicyber.spacetraders;

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.springframework.stereotype.Component
import retrofit2.Retrofit

@Component
class SpaceTradersApiFactory(private val tokenManager: TokenManager
) {
    private val apiInstance by lazy { create() }

    fun getApi(): SpaceTradersApi {
        return apiInstance
    }

    fun create(): SpaceTradersApi {
        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }


        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${tokenManager.getCurrentToken()}")
                    .addHeader("Accept", "application/json")
                    .build()

                val response = chain.proceed(request)

                if (!response.isSuccessful) {
                    val errorBody = response.peekBody(2048).string()
                    println("API Error: ${response.code} - $errorBody")
                }
                response
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.spacetraders.io/v2/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
            .create(SpaceTradersApi::class.java)
    }
}
