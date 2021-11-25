package com.example.tyrofit.Retrofit

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import com.example.tyrofit.Model.WorkoutData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://3.108.207.62:3003/api/user/workout/"

interface APIService {
    @GET("all?category_id=14")
    suspend fun getData(): WorkoutData

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}