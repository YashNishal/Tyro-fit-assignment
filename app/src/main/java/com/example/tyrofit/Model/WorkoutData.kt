package com.example.tyrofit.Model

data class WorkoutData(
    val success: Boolean,
    val message: String,
    val data: List<WorkoutSubData>
)
