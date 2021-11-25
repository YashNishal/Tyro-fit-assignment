package com.example.tyrofit.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tyrofit.Model.WorkoutData
import com.example.tyrofit.Model.WorkoutSubData
import com.example.tyrofit.Retrofit.APIService
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    var errorMessage: String by mutableStateOf("not_empty")
    var workoutData: WorkoutData = WorkoutData(
        success = true, message = "Fetched Successfully",
        data = listOf<WorkoutSubData>(WorkoutSubData())
    )


    fun getData() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                workoutData = apiService.getData()
                errorMessage = ""
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e("viewModel error : ", errorMessage)
            }
        }
    }
}