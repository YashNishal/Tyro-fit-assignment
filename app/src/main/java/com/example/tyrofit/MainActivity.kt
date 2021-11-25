package com.example.tyrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tyrofit.Model.WorkoutSubData
import com.example.tyrofit.Utils.gradientBackground
import com.example.tyrofit.ViewModel.MainViewModel


import com.example.tyrofit.ui.theme.*
import kotlinx.coroutines.delay


@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        @Suppress("DEPRECATION")
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = resources.getColor(R.color.transparent)
        }
        val vm = MainViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            TyrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val delay = remember { mutableStateOf(false)}
                    App(vm,delay)
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalFoundationApi
@Composable
fun App( vm: MainViewModel, delay: MutableState<Boolean>) {

    LaunchedEffect(Unit, block = {
        vm.getData()
        delay(500)
        delay.value = true
    })

//    val coroutineScope = rememberCoroutineScope()



    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(18.dp))
        TopBar()

        if (vm.errorMessage.isEmpty() && delay.value) {
            Grid(vm)
        }
        else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}


@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton()
        TitleText()
        FilterButton()
    }
}


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
fun BackButton() {
    Icon(
        modifier = Modifier
            .clip(CircleShape)
            .background(BackButtonColor)
            .padding(10.dp)
            .size(26.dp),
        painter = painterResource(id = R.drawable.back_icon),
        contentDescription = "Back",
        tint = Color.White
    )
}


@Composable
fun TitleText(text: String = "Cardio") {
    Text(
        text = text,
        fontSize = 22.sp,
        color = HeadingTextColor,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun FilterButton() {
    Icon(
        modifier = Modifier
            .clip(CircleShape)
            .gradientBackground(
                colors = listOf(
                    Gradient3,
                    Gradient2,
                    Gradient1
                ),
                45f
            )
            .padding(12.dp)
            .size(24.dp),
        painter = painterResource(id = R.drawable.ic_funnel),
        contentDescription = "Filter",
        tint = Color.White,
    )
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
                        painter = painterResource(id = R.drawable.image_15),
                        contentDescription = "image",
                        tint = Color.White,
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


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TyrofitTheme {
        val vm = MainViewModel()
        val delay = remember { mutableStateOf(true)}
        App(vm,delay)
    }
}

//@ExperimentalFoundationApi
//@Preview(showBackground = true)
//@Composable
//fun Loading() {
//    TyrofitTheme {
//        val loading = remember { mutableStateOf(true) }
//        App(loading)
//    }
//}