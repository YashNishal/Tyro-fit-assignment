package com.example.tyrofit.Model

data class WorkoutSubData (
    val id : Int = 1,
    val name : String = "1",
    val category_id : Int = 1,
    val trainer_id : Int = 1,
    val image : String = "1",
    val duration : String = "1",
    val min_calories : Int = 1,
    val max_calories : Int = 1,
    val description : String = "1",
    val difficulty_level : Int = 1,
    val createdAt : Long = 1,
    val updatedAt : Long = 1,
    val video : String = "1",
    val video_uploadedAt : Long = 1,
    val equipments : List<String> = listOf(),
    val workout_plans : List<WorkoutPlans> = listOf<WorkoutPlans>(),
    val trainer_name : String = "1",
    val category_name : String = "1",
    val difficulty_level_name : String="1"
)
