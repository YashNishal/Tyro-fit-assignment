package com.example.tyrofit.Components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tyrofit.Model.WorkoutSubData
import com.example.tyrofit.Utils.gradientBackground
import com.example.tyrofit.ViewModel.MainViewModel
import com.example.tyrofit.ui.theme.BackgroundColor
import com.example.tyrofit.ui.theme.WorkoutGrad1
import com.example.tyrofit.ui.theme.WorkoutGrad2
import com.example.tyrofit.ui.theme.WorkoutGrad3
import com.example.tyrofit.R





@ExperimentalFoundationApi
@Composable
fun Grid(vm: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 32.dp)
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 12.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            Log.e("VM " , vm.toString())
            Log.e("VM workoutData " , vm.workoutData.toString())
            Log.e("VM size " , vm.workoutData.data.toString())
            items(vm.workoutData.data.size) {
                WorkoutItem(vm.workoutData.data[it])
            }
        }
    }
}



@Composable
fun WorkoutItem(workout: WorkoutSubData) {
    Box(
        modifier = Modifier
            .padding(
                4.dp
            )
            .clip(RoundedCornerShape(10.dp))
            .gradientBackground(
                colors = listOf(
                    WorkoutGrad1,
                    WorkoutGrad2,
                    WorkoutGrad2,
                    WorkoutGrad2,
                    WorkoutGrad3,
                ),
                -8f
            )
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(BackgroundColor)
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.workout_image_2),
                        contentDescription = "image",
//                        tint = Color.Transparent,
                        modifier = Modifier.size(80.dp)
                    )
                }
                // title
                Text(
                    text = workout.name,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                )
                // subtitle
                Text(
                    text = "With ${workout.trainer_name}",
                    color = Color.White,
                    fontSize = 6.sp,
                    modifier = Modifier.padding(top = 3.dp)
                )


                // bottom row with difficulty and time
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // difficulty
                    Text(
                        text = workout.difficulty_level_name, color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    // time
                    Text(
                        text = (workout.duration.toInt()/60).toString(), color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}